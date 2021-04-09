package com.sberStudy.java.homeWork.pivovarova.lesson16;

import org.h2.tools.Server;
import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FibonacciTest {
    private static CalculatorFi calculator;

//    @BeforeAll
    public void createTable() throws SQLException {
        DateSourceHelper.createDb();
        Server.createTcpServer().start();

    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        calculator = CalculatorFibonacciHandler.cache(new CalculatorFibonacci());
        System.out.println(calculator.fibonachi(5));
        System.out.println(calculator.fibonachi(7));
        System.out.println(calculator.fibonachi(14));
        System.out.println(calculator.fibonachi(18));
        System.out.println(calculator.fibonachi(9));
        System.out.println(calculator.fibonachi(3));
        System.out.println(calculator.fibonachi(12));
        System.out.println(calculator.fibonachi(7));
        System.out.println(calculator.fibonachi(14));
        System.out.println(calculator.fibonachi(6));
        System.out.println(calculator.fibonachi(8));
        System.out.println(calculator.fibonachi(5));
        System.out.println(calculator.fibonachi(21));
        System.out.println(calculator.fibonachi(16));
        System.out.println(calculator.fibonachi(14));
        System.out.println(calculator.fibonachi(5));
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testWithExecutorService() {
        final int THREADS_COUNT = 16;
        final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);
        long start = System.currentTimeMillis();
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5,7,14,18,9,3,12,7,14,6,8,5,21,16,14,5));
        CalculatorFi calculator = CalculatorFibonacciHandler.cache(new CalculatorFibonacci());
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Integer integer : numbers) {
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " вычисляет числа фибоначчи для числа " +
                            integer + ", результат: " + calculator.fibonachi(integer));
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
