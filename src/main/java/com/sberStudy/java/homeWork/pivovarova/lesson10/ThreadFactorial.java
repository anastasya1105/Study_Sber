package com.sberStudy.java.homeWork.pivovarova.lesson10;

import com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator.Calculator;
import com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator.CalculatorHandler;
import com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator.CalculatorImpl;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadFactorial {
    public static void createThread(String fileName) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Calculator calculator= CalculatorHandler.cache(new CalculatorImpl());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), "UTF-8"))) {
            String line;
            while ((line = reader.readLine())!= null) {
                int num = Integer.parseInt(line);
                executorService.execute(new Runnable() {
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + " " + num + " = " + calculator.calc(num));
                    }
                });
            }
            executorService.shutdown();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createThread("src/test/resources/lesson10/Integer.txt");
    }
}
