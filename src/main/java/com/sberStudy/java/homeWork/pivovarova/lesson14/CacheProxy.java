package com.sberStudy.java.homeWork.pivovarova.lesson14;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CacheProxy<T> implements InvocationHandler{
    private final T delegate;
    private final ConcurrentMap<String, ValueForCacheMap> cacheMap = new ConcurrentHashMap<>();
    private final String cacheDir;
    private final Lock lock = new ReentrantLock();

    public CacheProxy(T delegate, String cacheDir) {
        this.delegate = delegate;
        this.cacheDir = cacheDir;
    }

    public static <T> T cache(T delegate, String cacheDir) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheProxy(delegate, cacheDir)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheMethod = method.getAnnotation(Cache.class);
        CacheType cacheType = cacheMethod.cacheType();
        boolean zip = cacheMethod.zip();
        Object cacheValue;
        if (!method.isAnnotationPresent(Cache.class)) {
            return invoke(method, args);
        }
        else {
            String key = getCacheKey(method, zip);
            ValueForCacheMap valueForCacheMap;
            lock.lock();
            try {
                if (cacheMap.containsKey(getCacheKey(method, zip))) {
                    valueForCacheMap = cacheMap.get(key);
                } else {
                    valueForCacheMap = new ValueForCacheMap(method, cacheDir, key);
                    cacheMap.put(key, valueForCacheMap);
                }
                if (cacheType.equals(CacheType.FILE)) {
                    try {
                        synchronized (this) {
                            cacheValue = valueForCacheMap.getFromFile(args);
                        }
                        return cacheValue;
                    } catch (FileException e) {
                        synchronized (this) {
                            return valueForCacheMap.putInFile(args, invoke(method, args));
                        }
                    }
                }
                if (cacheType.equals(CacheType.IN_MEMORY)) {
//                    synchronized (this) {
                        cacheValue = valueForCacheMap.getFromCache(args);
//                    }
                    if (cacheValue != null) {
                        return cacheValue;
                    } else {
//                        synchronized (this) {
                            return valueForCacheMap.putInCache(args, invoke(method, args));
//                        }
                    }
                } else {
                    throw new IllegalArgumentException("Для данного метода неправильно указан CacheType. Возможные варианты: CacheType.FILE или CacheType.IN_MEMORY");
                }
            }finally {
                lock.unlock();
            }
        }
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
    private String getCacheKey(Method method, boolean zip) {
        String key;
        if (zip) {
            key = method.getAnnotation(Cache.class).fileName() + ".zip";
        }
        else {
            key = method.getAnnotation(Cache.class).fileName();
        }
        if (key.isEmpty()) {
            if (zip) {
                key = method.getName() + ".zip";
            }
            else {
                key = method.getName();
            }
        }
        return key;
    }
}
