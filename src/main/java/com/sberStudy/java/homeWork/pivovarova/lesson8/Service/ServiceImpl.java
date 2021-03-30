package com.sberStudy.java.homeWork.pivovarova.lesson8.Service;

import com.sberStudy.java.homeWork.pivovarova.lesson8.forExample.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service{

    EmployeeManager employeeManager = new EmployeeManager();
    @Override
    public String findBirthday(People people) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return people.getBirthday();
    }

    @Override
    public List<Employee> doHardWork(int personalFileNumber) {
        List<Employee> newList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int newPersonalFileNumber = (int) Math.random()*6;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newList.add(new Employee("new Employee", "new surname", "new patronymic",true, "new", new Address("new city", "new street", i), new Passport(1111,111_111,new Date(),"Somewhere"), "new position", newPersonalFileNumber));
        }
        return newList;
    }

    @Override
    public Employee findEmployeeFromZip(int personalFileNumber, String surname) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return employeeManager.getEmployeeList()
                .stream()
                .filter(employee -> employee.getSurname().equals(surname))
                .findAny().orElse(null);
    }

    @Override
    public Employee findEmployee(int personalFileNumber, String surname) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return employeeManager.getEmployeeList()
                .stream()
                .filter(employee -> employee.getPersonalFileNumber() == personalFileNumber)
                .findAny().orElse(null);
    }

}
