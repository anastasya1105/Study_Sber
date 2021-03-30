package com.sberStudy.java.homeWork.pivovarova.lesson8.forExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeManager {
    public List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("Vladimir","Petrov","Andreevich",true, "12.04.1991", new Address("Moscow", "Mayakovskaya", 11), new Passport(1111,111_111,new Date(),"Somewhere"), "manager", 1562357);
        Employee employee2 = new Employee("Dmitriy","Ivanov","Vasilievich",true, "11.06.1995", new Address("Moscow", "Novoslobodskaya", 15), new Passport(2222,222_222,new Date(),"Somewhere"), "programmer", 1574884);
        Employee employee3 = new Employee("Petr","Kolosov","Matveevich",true, "30.10.1990", new Address("Moscow","Turgenevskaya",146), new Passport(2134,235_234,new Date(),"Somewhere"), "accountant", 145923);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        return employees;
    }
}
