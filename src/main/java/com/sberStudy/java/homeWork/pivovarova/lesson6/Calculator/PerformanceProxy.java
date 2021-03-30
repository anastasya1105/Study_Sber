package com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PerformanceProxy implements InvocationHandler  {

    private final Object delegate;

    public PerformanceProxy(Object delegate) {
        this.delegate = delegate;
    }

    public static <T> T cache(Object delegate) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new PerformanceProxy(delegate)
        );
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (!method.isAnnotationPresent(Metric.class)) {
            return method.invoke(delegate, args);
        }
        long currentTime = System.nanoTime();
        Object object = method.invoke(delegate, args);
        System.out.println("Время работы метода: " + (System.nanoTime() - currentTime) + " (в наносек)");
        return object;
    }
}
