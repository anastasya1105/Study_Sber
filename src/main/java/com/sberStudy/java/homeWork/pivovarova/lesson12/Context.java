package com.sberStudy.java.homeWork.pivovarova.lesson12;

public interface Context {
    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    void interrupt();

    boolean isFinished();

    void work();
}
