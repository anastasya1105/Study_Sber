package com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class CalculatorHandler implements InvocationHandler {

    private final Object delegate;
    private final Map<Object, Object> cacheMap = new HashMap<>();

    public CalculatorHandler(Object delegate) {
        this.delegate = delegate;
    }

    public static <T> T cache(Object delegate) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CalculatorHandler(delegate)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) {
            return invoke(method, args);
        }

        if (!cacheMap.containsKey(key(method, args))) {
            System.out.println("Delegation of " + method.getName());
            Object result = invoke(method, args);
            cacheMap.put(key(method, args), result);
        }

        return cacheMap.get(key(method, args));
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(delegate, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Object key(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }

}
