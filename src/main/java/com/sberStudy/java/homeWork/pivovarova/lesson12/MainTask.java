package com.sberStudy.java.homeWork.pivovarova.lesson12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTask {
    public static void main(String[] args) {
        Task<Integer> task = new Task<Integer>(() -> {
            Thread.sleep(5000);
            return (int) (12 + Math.random()* 15);
        });

        for (int i = 0; i < 23; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " " + task.get());
            }).start();
        }
    }
}
