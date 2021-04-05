package com.sberStudy.java.homeWork.pivovarova.lesson14.Service;



import com.sberStudy.java.homeWork.pivovarova.lesson14.forExample.Employee;
import com.sberStudy.java.homeWork.pivovarova.lesson14.forExample.People;
import com.sberStudy.java.homeWork.pivovarova.lesson14.Cache;
import com.sberStudy.java.homeWork.pivovarova.lesson14.CacheType;

import java.util.List;

public interface Service {

    @Cache(cacheType = CacheType.IN_MEMORY,fileName = "BirthdayData")
    String findBirthday(People people);

    @Cache(cacheType = CacheType.IN_MEMORY,maxListElementsCached = 8)
    List<Employee> doHardWork(int personalFileNumber);

    @Cache(cacheType = CacheType.FILE, fileName = "EmployeeCache", zip = true)
    Employee findEmployeeFromZip(int personalFileNumber);

    @Cache(cacheType = CacheType.FILE)
    Employee findEmployee(int personalFileNumber);

}
