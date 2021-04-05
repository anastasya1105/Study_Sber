package com.sberStudy.java.homeWork.pivovarova.lesson16;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Задание: Разработать продвинутый кэш, который помнит о кэшированных данных после перезапуска приложения.
В данный момент прокси проверяет, пустой ли кэш, если да, то проверяет, есть ли необходимый элемент в дб, если нет,
то делегирует вычисление, после чего записывает в память(мапу) и в дб.
Возможно, я не правильно поняла задание, и в памяти сожранять ничего не нужно было, а только в дб,
тогда правильным нужно считать метод invoke, который сейчас закоментирован.
 */
public class CalculatorFibonacciHandler implements InvocationHandler {
    private final Object delegate;
    private final ConcurrentMap<Object, Object> cacheMap = new ConcurrentHashMap<>();
    private static final MySQLite myTable = new MySQLite();
    private static final Lock lock = new ReentrantLock();
    private volatile Object result;
    private static final Object monitor = new Object();

    public CalculatorFibonacciHandler(Object delegate) {
        this.delegate = delegate;
    }

    public static <T> T cache(Object delegate) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CalculatorFibonacciHandler(delegate)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cachable.class)) {
            return invoke(method, args);
        }
        synchronized (monitor) {
            int n = (int) args[0] + 1;
            if (cacheMap.isEmpty()) {
                if (myTable.thisItem(n)) {
                    result = myTable.receiveList(n);
                    cacheMap.put(key(method, args), result);
                    System.out.println("Кэш пуста, но есть данные в дб");
                    return result;
                }
            }
            if (!cacheMap.containsKey(key(method, args))) {
                if (myTable.thisItem(n)) {
                    result = myTable.receiveList(n);
                    cacheMap.put(key(method, args), result);
                    System.out.println("Необходимого числа в Кэше нет, есть в дб");
                } else {
                    System.out.println("Delegation of " + method.getName());
                    result = invoke(method, args);
                    cacheMap.put(key(method, args), result);
                    myTable.addNumber(result);
                }
                return result;
            }
            System.out.println("Значение получено из Кэша");
            return cacheMap.get(key(method, args));
        }
    }

//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        if (!method.isAnnotationPresent(Cachable.class)) {
//            return invoke(method, args);
//        }
//        int n = (int) args[0] + 1;
//        synchronized (monitor) {
//            if (myTable.thisItem(n)) {
//                result = myTable.receiveList(n);
//                System.out.println("Есть данные в дб");
//            } else {
//                System.out.println("Delegation of " + method.getName());
//                result = invoke(method, args);
//                myTable.addNumber(result);
//            }
//            return result;
//        }
//
//    }

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
