package com.sberStudy.java.homeWork.pivovarova.lesson14.forExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeManager {
    public List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("Vladimir","Petrov","Andreevich",true, "12.04.1991", new Address("Moscow", "Mayakovskaya", 11), new Passport(3652,189_584,new Date(),"Somewhere"), "manager", 1562357);
        Employee employee2 = new Employee("Dmitriy","Ivanov","Vasilievich",true, "11.06.1995", new Address("Moscow", "Novoslobodskaya", 15), new Passport(2461,143_654,new Date(),"Somewhere"), "programmer", 1574884);
        Employee employee3 = new Employee("Petr","Kolosov","Matveevich",true, "30.10.1990", new Address("Moscow","Turgenevskaya",146), new Passport(1254,351_636,new Date(),"Somewhere"), "accountant", 1459231);
        Employee employee4 = new Employee("Georgiy","Farrell","Yuryevich",true, "16.05.1994", new Address("Moscow", "Tsvetochnaya", 72), new Passport(1368,239_684,new Date(),"Somewhere"), "manager", 1466932);
        Employee employee5 = new Employee("Denis","Porter","Mikhaylovich",true, "26.08.1989", new Address("Moscow", "Stroiteley", 69), new Passport(2681,168_526,new Date(),"Somewhere"), "programmer", 1516948);
        Employee employee6 = new Employee("Elena","Clapton","Valeryevna",false, "06.10.1992", new Address("Moscow","Leningradskij",113), new Passport(2369,345_264,new Date(),"Somewhere"), "accountant", 1496325);
        Employee employee7 = new Employee("Oksana","Milton","Evgenyevna",false, "01.09.1985", new Address("Moscow", "Zheleznodorozhnaya", 98), new Passport(1461,136_694,new Date(),"Somewhere"), "manager", 1523648);
        Employee employee8 = new Employee("Svetlana","Bargeman","Nikolayevna",false, "17.06.1993", new Address("Moscow", "Dorozhnaya", 43), new Passport(2387,236_226,new Date(),"Somewhere"), "programmer", 1518749);
        Employee employee9 = new Employee("Olga","Jenkin","Yuryevna",false, "29.12.1988", new Address("Moscow","Dybenko",9), new Passport(2134,236_854,new Date(),"Somewhere"), "accountant", 1483649);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        employees.add(employee7);
        employees.add(employee8);
        employees.add(employee9);
        return employees;
    }
}
