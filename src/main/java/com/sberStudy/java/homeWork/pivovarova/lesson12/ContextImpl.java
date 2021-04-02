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
    public void interrupt() {

        lock.lock();
        try {
            for (ContextThread thread : threads) {
                thread.interrupt();
                interruptedTaskCount++;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount;
    }

    @Override
    public boolean isFinished() {
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
                    completedTaskCount++;
                    threads.remove(this);
                }
            }
            catch (Exception e) {
                synchronized (this) {
                    exceptionsQueue.add(e);
                    failedTaskCount++;
                }
            }
            if (threads.size() == 0){
                finishWork();
            }
        }

        private synchronized void finishWork() {
            new Thread(callback).start();
        }
    }

}

