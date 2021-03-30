package com.sberStudy.java.homeWork.pivovarova.lesson9;

import com.sberStudy.java.homeWork.pivovarova.lesson8.forExample.Employee;
import com.sberStudy.java.homeWork.pivovarova.lesson8.forExample.EmployeeManager;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestStreams {

    EmployeeManager employeeManager = new EmployeeManager();
    List<Employee> employees = employeeManager.getEmployeeList();

    @Test
    public void test1() {
        Map<String, Object> map = Streams.of(employees)
                .filter(p -> !p.isMale())
                .transform(p -> p.getAdress())
                .toMap(p -> p.getStreet(), p -> p);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}
