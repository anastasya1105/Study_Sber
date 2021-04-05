package com.sberStudy.java.homeWork.pivovarova.lesson12;

/*
Т.к. в задании сказано, что массив тасков, это задания которые ExecutionManager
должен выполнять параллельно, я сделала на каждое задание свой Thread, и когда мы вызываем метод interrupted,
все потоки уже выполняются и метод interrupt() ничего остановить не может...
Чтобы продемонстрировать, что метод все-таки работает, я добавила условие, что он останавливает задания, которые временно остановленны -
TIМED WAITING, сейчас это условие закоментировано в методе interrupt() класса ContextImpl.
Возможно я не правильно поняла условие, и не абсолютно все задачи должны выполняться парально
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
                    int time = (int) (1000 + Math.random()*2000);
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
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
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.interrupt();

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
