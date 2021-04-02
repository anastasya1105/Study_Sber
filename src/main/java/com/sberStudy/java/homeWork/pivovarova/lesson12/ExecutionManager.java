package com.sberStudy.java.homeWork.pivovarova.lesson12;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
