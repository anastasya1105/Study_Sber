package com.sberStudy.java.homeWork.pivovarova.lesson12;

public class ExecutionManagerImpl implements ExecutionManager{
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        Context context = new ContextImpl(callback, tasks);
        context.work();
        return context;
    }
}
