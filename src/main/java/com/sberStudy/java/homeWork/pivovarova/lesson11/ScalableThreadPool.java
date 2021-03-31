package com.sberStudy.java.homeWork.pivovarova.lesson11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ScalableThreadPool implements ThreadPool{
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final Object lock = new Object();
    private final List<ScalableThread> workers;
    private final int min;
    private final int max;
    private volatile boolean work;

    public ScalableThreadPool(int min, int max) {
        if (min > max || min < 1) {
            throw new IllegalArgumentException("Check parameters in constructor of ScalableThreadPool");
        }
        this.min = min;
        this.max = max;
        workers = new ArrayList<>(max);
        for (int i = 0; i < min; i++) {
            workers.add(new ScalableThread("New my Thread " + i));
        }
    }

    private class ScalableThread extends Thread {
        public ScalableThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable task;
                synchronized (lock) {
                    while (tasks.isEmpty()) {
                        if (workers.size() > min) {
                            System.out.println("При отсутствии задания в очереди, количество потоков уменьшается до значения " + min + ", было: " + workers.size());
                            workers.remove(ScalableThread.this);
                        }
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException("Killed by ScalableThreadPool", e);
                        }
                    }
                    task = tasks.poll();
                }
                    try {
                        task.run();

                    } catch (Exception e) {
                        throw new RuntimeException("Smth bad in task", e);
                    }
            }
        }
    }

    @Override
    public void start() {
        work = true;
        workers.forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (lock) {
            createThread();
            tasks.add(runnable);
            lock.notify();
        }
    }

    private void createThread() {
        if (workers.size() == max) {return;}
        if (!work) {
            ScalableThread scalableThread = new ScalableThread("Еще один");
            System.out.println("Поступило ");
            workers.add(scalableThread);
        }
        else {
            if (tasks.size() > workers.size()) {
                ScalableThread scalableThread = new ScalableThread("Еще один");
                workers.add(scalableThread);
                System.out.println("При добавлении нового задания в очередь, количество запущенных потоков было увеличено до " + workers.size());
                scalableThread.start();
            }
        }
    }
}
