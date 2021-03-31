package com.sberStudy.java.homeWork.pivovarova.lesson11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool{
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final Object lock = new Object();
    private final List<FixedThread> workers;

    public FixedThreadPool(int nThreads) {
        workers = new ArrayList<>(nThreads);
        for (int i = 0; i < nThreads; i++) {
            workers.add(new FixedThread("new Thread " + i));
        }
    }

    @Override
    public void start() {
        workers.forEach(Thread ::start);
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (lock) {
            tasks.add(runnable);
            lock.notify();
//            start();
        }
    }

    private class FixedThread extends Thread {
        public FixedThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                Runnable task;
                synchronized (lock) {
                    while (tasks.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.poll();
                }
                    try {
                        task.run();
                    } catch (Exception e) {
                        throw new RuntimeException("Something bad in task", e);
                    }

            }
        }
    }

}
