package com.sberStudy.java.homeWork.pivovarova.lesson12;

/*
Не работает метод interrupted, возможно, потому что массив тасков, это задания которые ExecutionManager
должен выполнять параллельно, и когда мы вызываем метод interrupted, все потоки уже выполняются и
он ничего остановить не может... Или я что-то не правильно поняла...
 */

public class MainExecutionManager {
    public static void main(String[] args) {
        Runnable callBack = () -> {
            System.out.println("Callback happened");
        };
        Runnable[] tasks = new Runnable[50];
        for (int i = 0; i < tasks.length; i++) {
            final int ii = i;
            tasks[i] = () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task "+ii);
                if (ii%5==0) {
                    throw new IllegalArgumentException("exception "+ii);
                }
            };
        }
        ExecutionManagerImpl executionManager = new ExecutionManagerImpl();
        Context context = executionManager.execute(callBack, tasks);
        try {
            Thread.sleep(1010);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        context.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("completed " + context.getCompletedTaskCount());
        System.out.println("failed " + context.getFailedTaskCount());
        System.out.println("interrupted " + context.getInterruptedTaskCount());
        System.out.println("isfinished " + context.isFinished());
    }
}
