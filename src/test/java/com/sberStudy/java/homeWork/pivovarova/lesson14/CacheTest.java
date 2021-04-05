package com.sberStudy.java.homeWork.pivovarova.lesson14;

import com.sberStudy.java.homeWork.pivovarova.lesson14.Service.Service;
import com.sberStudy.java.homeWork.pivovarova.lesson14.Service.ServiceImpl;

import com.sberStudy.java.homeWork.pivovarova.lesson14.forExample.Employee;
import com.sberStudy.java.homeWork.pivovarova.lesson14.forExample.EmployeeManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheTest {
    ServiceImpl service = new ServiceImpl();
    String cacheDir = "src/test/resources/lesson14";
    EmployeeManager employeeManager = new EmployeeManager();
    List<Employee> employees;
    Service cache;

    @Test
    public void testCacheInMemory() {
        /*
        Данный метод просчитанный результат хранит в памяти JVM.
        Без использования синхронизации время выподнения 15099,15038,15046,15052,15047,15046
        При использовании ReentrantLock() на строке 44 в классе CacheProxy на весь блок чтения и записи информации:
        время выполнения 45105, 45111, 45111,45089, 45107,45094, оставила её.
        При использовании блоков синхронизации на вызов методов записи и синхронизации в классе CacheProxy
        в строках 65 и 71: время выполнения 65134,65138,65150,65148,65145,56123.
         */
        cache = CacheProxy.cache(service, cacheDir);
        employees = employeeManager.getEmployeeList();
        final int THREADS_COUNT = 27;
        final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);
        long start = System.currentTimeMillis();
        List<Employee> listEmployee = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (Employee e : employees) {
                listEmployee.add(e);
            }
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Employee employee : listEmployee) {
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " " + cache.findBirthday(employee));
                    cdl.countDown();
                }
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(System.currentTimeMillis() - start);

    }

    @Test
    public void testCacheInFile() {
        /*
        Данный метод просчитанный результат хранит в файле, перед каждым запуском все созданные файлы удалялись.
        Без использования синхронизации время выподнения 10117 10111,10129,10102,10112,10117
        При использовании ReentrantLock() на строке 44 в классе CacheProxy на весь блок чтения и записи информации:
        время выполнения 45275,45191,45236,45199,45197,45189
        При использовании блоков синхронизации на вызов методов записи и синхронизации в классе CacheProxy
        в строках 54 и 59: время выполнения 45210, 45196,45192,45188,45218,45204.
         */
        cache = CacheProxy.cache(service, cacheDir);
        final int THREADS_COUNT = 20;
        final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);
        long start = System.currentTimeMillis();
        List<Integer> personalFileNumberList = new ArrayList<>(Arrays.asList(1562357, 1574884, 1459231, 1466932, 1516948, 1496325, 1523648, 1518749, 1483649, 1466932, 1516948, 1496325, 1523648, 1562357, 1574884, 1523648, 1518749, 1483649, 1466932, 1516948));
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Integer integer : personalFileNumberList) {
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " " + cache.findEmployee(integer));
                    cdl.countDown();
                }
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(System.currentTimeMillis() - start);

    }
    @Test
    public void testCacheInFileZip() {
        /*
        Данный метод просчитанный результат хранит в Zipфайле, перед каждым запуском все созданные файлы удалялись.
        Без использования синхронизации время выподнения 10116,10121,10107,10126,10121,10123 + 2 раза была ошибка.
        При использовании ReentrantLock() на строке 44 в классе CacheProxy на весь блок чтения и записи информации:
        время выполнения 45234,45219,45219,45199,45184,45189,45217
        При использовании блоков синхронизации на вызов методов записи и синхронизации в классе CacheProxy
        в строках 54 и 59: время выполнения 45180, 45198,45256,45175,45188,45188.
         */
        cache = CacheProxy.cache(service, cacheDir);
        final int THREADS_COUNT = 20;
        final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);
        long start = System.currentTimeMillis();
        List<Integer> personalFileNumberList = new ArrayList<>(Arrays.asList(1562357, 1574884, 1459231, 1466932, 1516948, 1496325, 1523648, 1518749, 1483649, 1466932, 1516948, 1496325, 1523648, 1562357, 1574884, 1523648, 1518749, 1483649, 1466932, 1516948));
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Integer integer : personalFileNumberList) {
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " " + cache.findEmployeeFromZip(integer));
                    cdl.countDown();
                }
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(System.currentTimeMillis() - start);
    }

}
