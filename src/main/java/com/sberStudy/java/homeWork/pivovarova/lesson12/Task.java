package com.sberStudy.java.homeWork.pivovarova.lesson12;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task<T> {

    private final Callable<? extends T> callable;
    private T result;
    private volatile boolean isResult;
    private boolean isException = false;
    private Lock lock = new ReentrantLock();
    private volatile boolean firstThread = true;
    private final Object objectLock = new Object();


    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (isResult) return returnResult();
        synchronized (this) {
            if (!isResult) {
                calculationResult();
            }
        }
        try {
            lock.lock();
            if (!isResult) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            lock.unlock();
        }
        return returnResult();
    }

    private T calculationResult() {
        try {
            result = callable.call();
        } catch (Exception e) {
            isException = true;
        }
        isResult = true;
        notifyAll();
        return returnResult();
    }

    private T returnResult() {
        if (isException) throw new TaskException("при просчете результата произошел Exception");
        return result;
    }



}
