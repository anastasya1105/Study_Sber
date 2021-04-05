package com.sberStudy.java.homeWork.pivovarova.lesson12;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContextImpl implements Context {

    private final Runnable[] tasks;
    private final Runnable callback;
    private final List<ContextThread> threads = new ArrayList<>();
    private final Queue<Exception> exceptionsQueue = new ArrayDeque<>();
    private volatile int completedTaskCount = 0;
    private volatile int failedTaskCount = 0;
    private volatile int interruptedTaskCount = 0;
    private final Lock lock = new ReentrantLock();
    private volatile int taskCount;

    public ContextImpl(Runnable callback, Runnable[] tasks) {
        this.callback = callback;
        this.tasks = tasks;
        taskCount = tasks.length;
    }

    @Override
    public void work() {
        Arrays.stream(tasks)
                .map(task -> new ContextThread(task))
                .forEach(threads::add);
        threads.forEach(Thread::start);
    }

    @Override
    public int getCompletedTaskCount() {
        return completedTaskCount;
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount;
    }

    @Override
    public synchronized void interrupt() {
        for (ContextThread thread : threads) {
            if (thread.getState().equals(Thread.State.NEW)) {
                thread.interrupt();
                interruptedTaskCount++;
            }
//            if (thread.getState().equals(Thread.State.TIMED_WAITING)) {
//                thread.interrupt();
//                interruptedTaskCount++;
//            }
        }
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount;
    }

    @Override
    public synchronized boolean isFinished() {
        return threads.isEmpty();

    }


    private class ContextThread extends Thread {
        private final Runnable target;

        private ContextThread(Runnable target) {
            this.target = target;
        }

        @Override
        public void run() {
            try {

                synchronized (this) {
                    target.run();
                    if (!Thread.currentThread().isInterrupted()) {
                        completedTaskCount++;
                        Thread.currentThread().interrupt();
                        taskCount--;
                    }
                }
            }
            catch (Exception e) {
                synchronized (this) {
                    if (!Thread.currentThread().isInterrupted()) {
                        exceptionsQueue.add(e);
                        failedTaskCount++;
                        Thread.currentThread().interrupt();
                        taskCount--;
                    }
                }
            }
            if (taskCount == 0){
                finishWork();
            }
        }

        private synchronized void finishWork() {
            Thread thread = new Thread(callback);
            thread.start();
            thread.interrupt();
        }
    }

}

