package com.sberStudy.java.homeWork.pivovarova.lesson8;

import com.sberStudy.java.homeWork.pivovarova.lesson8.Service.Service;
import com.sberStudy.java.homeWork.pivovarova.lesson8.Service.ServiceImpl;
import com.sberStudy.java.homeWork.pivovarova.lesson8.forExample.Employee;
import com.sberStudy.java.homeWork.pivovarova.lesson8.forExample.EmployeeManager;
import org.junit.Test;

import java.util.List;

public class CacheTest {

    ServiceImpl service = new ServiceImpl();
    String cacheDir = "src/test/resources/lesson8";
    EmployeeManager employeeManager = new EmployeeManager();
    List<Employee> employees;
    Service cache;


    @Test
    public void testCacheInMemory() {
        cache = CacheProxy.cache(service, cacheDir);
        employees = employeeManager.getEmployeeList();
        System.out.println("Calculating " +cache.findBirthday(employees.get(0)));
        System.out.println("Calculating " +cache.findBirthday(employees.get(2)));
        System.out.println("Calculating " +cache.findBirthday(employees.get(1)));
        System.out.println("From cache "+cache.findBirthday(employees.get(1)));
        System.out.println("From cache "+cache.findBirthday(employees.get(2)));
        System.out.println("From cache "+cache.findBirthday(employees.get(0)));
    }

    @Test
    public void testCacheInFile() {
        cache = CacheProxy.cache(service, cacheDir);
        System.out.println("find employee from file by the first parameter");
        System.out.println(cache.findEmployee(1562357, "Petrov"));
        System.out.println(cache.findEmployee(1574884, "Ivanov"));
        System.out.println(cache.findEmployee(145923, "Kolosov"));
        System.out.println(cache.findEmployee(1562357, "Ivanov"));
        System.out.println(cache.findEmployee(1574884, "Ivanov"));
        System.out.println(cache.findEmployee(1562357, "Ivanov"));
        System.out.println(cache.findEmployee(145923, "Ivanov"));
        System.out.println("find employee from zip-file by the second parameter");
        System.out.println(cache.findEmployeeFromZip(1562357, "Petrov"));
        System.out.println(cache.findEmployeeFromZip(1574884, "Ivanov"));
        System.out.println(cache.findEmployeeFromZip(145923, "Kolosov"));
        System.out.println(cache.findEmployeeFromZip(1562357, "Ivanov"));
        System.out.println(cache.findEmployeeFromZip(1574884, "Kolosov"));
        System.out.println(cache.findEmployeeFromZip(1562357, "Petrov"));
        System.out.println(cache.findEmployeeFromZip(145923, "Kolosov"));
    }

    @Test
    public void testCacheReturningList() {
        cache = CacheProxy.cache(service, cacheDir);
        List<Employee> list = cache.doHardWork(2);
        for (Employee e : list) {
            System.out.println(e);
        }
    }
}