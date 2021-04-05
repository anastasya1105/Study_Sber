package com.sberStudy.java.homeWork.pivovarova.lesson16;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLite {
    private static Connection connection;
    private volatile static Statement stmt;

    public MySQLite() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:fibonacci.db");
            stmt = connection.createStatement();
            if (connection.isClosed()) {
                System.out.println("Соединение с БД разорвано");
            }
        } catch (SQLException | ClassNotFoundException troubles) {
            System.out.println("Не удалось соединиться с БД");
            troubles.printStackTrace();
        }
    }

    public void createTableEx() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS fibonacci (id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT);");
    }

    public synchronized boolean thisItem(int n) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM fibonacci WHERE id = ")
                .append(n)
                .append(";");
        String query = queryBuilder.toString();
        try (ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String a = rs.getString("number");
                if (a != null) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            System.out.println("Не удалось соединиться с БД");
            throwables.printStackTrace();
        }
        return false;
    }

    public Object receiveList(int n) {
        List<Integer> list = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM fibonacci WHERE id < ")
                .append(n + 1)
                .append(";");
        String query = queryBuilder.toString();
        try (ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String number = rs.getString("number");
                list.add(Integer.parseInt(number));
            }
        } catch (SQLException throwables) {
            System.out.println("Не удалось соединиться с БД");
            throwables.printStackTrace();
            disconnect();
        }
        return list;
    }

    public void addNumber(Object result) {
        List<Integer> list = (List) result;
        int index = 0;
        int number = 0;
        if (!thisItem(1)) {
            try {
                stmt.executeUpdate("INSERT INTO fibonacci (number) VALUES (0);");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM fibonacci WHERE id = (SELECT MAX(ID)  FROM fibonacci);")) {
            while (rs.next()) {
                index = rs.getInt("id");
            }

            for (int i = index; i < list.size(); i++) {

                number = list.get(i);
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("INSERT INTO fibonacci (number) VALUES ('")
                        .append(number)
                        .append("');");
                String query = queryBuilder.toString();
                stmt.executeUpdate(query);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }

    }

    public void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
