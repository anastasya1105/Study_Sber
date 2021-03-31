package com.sberStudy.java.homeWork.pivovarova.lesson11;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        ScalableThreadPool threadPool = new ScalableThreadPool(4, 6);
        FixedThreadPool threadPool = new FixedThreadPool(4);
        for (int i = 0; i < 40; i++) {
            final int ii = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + ii);
            });
        }
        threadPool.start();
        Thread.sleep(7000);
        System.out.println("Вторая часть заданий");

        for (int i = 0; i < 35; i++) {
            final int ii = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task 2 " + ii);
            });
        }

    }
}
