package com.sberStudy.java.homeWork.pivovarova.lesson8.Service;



import com.sberStudy.java.homeWork.pivovarova.lesson8.Cache;
import com.sberStudy.java.homeWork.pivovarova.lesson8.CacheType;
import com.sberStudy.java.homeWork.pivovarova.lesson8.forExample.Employee;
import com.sberStudy.java.homeWork.pivovarova.lesson8.forExample.People;

import java.util.Date;
import java.util.List;

public interface Service {

    @Cache(cacheType = CacheType.IN_MEMORY,fileName = "BirthdayData")
    String findBirthday(People people);

    @Cache(cacheType = CacheType.IN_MEMORY,maxListElementsCached = 8)
    List<Employee> doHardWork(int personalFileNumber);

    @Cache(cacheType = CacheType.FILE, fileName = "EmployeeCache", identifyByArgNumbers = {1}, zip = true)
    Employee findEmployeeFromZip(int personalFileNumber, String surname);

    @Cache(cacheType = CacheType.FILE, fileName = "EmployeeCache", identifyByArgNumbers = {0})
    Employee findEmployee(int personalFileNumber, String surname);

}
