package com.sberStudy.java.homeWork.pivovarova.lesson15.Salary;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*
    Для удобства и читаймости кода, я вынесла часть логики в отдельные методы,
    при необходимости их можно вынести в отдельные классы.
 */
public class SalaryHtmlReportNotifier {
    private Connection connection;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.connection = databaseConnection;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        String query = "select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name";
        try {
            ResultSet results = execute(departmentId, dateFrom, dateTo, query);
            String result = createReport(results);
            send("mail.google.com", "Monthly department salary report", recipients, result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private ResultSet execute(String departmentId, LocalDate dateFrom, LocalDate dateTo, String query) throws SQLException {
        ResultSet results = null;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(0, departmentId);
            ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
            ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));
            results = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


    private String createReport(ResultSet results) throws SQLException {
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;
        while (results.next()) {
            resultingHtml.append("<tr>");
            resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>");
            resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>");
            resultingHtml.append("</tr>");
            totals += results.getDouble("salary");
        }
        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");
        return resultingHtml.toString();
    }


    private static void send(String host, String subject, String recipients, String resultingHtml) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            helper.setText(resultingHtml.toString(), true);
            helper.setSubject(subject);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
