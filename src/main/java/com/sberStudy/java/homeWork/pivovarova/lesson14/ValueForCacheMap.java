package com.sberStudy.java.homeWork.pivovarova.lesson14;

import com.sberStudy.java.homeWork.pivovarova.lesson14.Cache;
import com.sberStudy.java.homeWork.pivovarova.lesson14.FileException;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ValueForCacheMap {
    private final ConcurrentMap<String, Object> mapValue = new ConcurrentHashMap<>();
    private final Method method;
    private final String dir;
    private final String nameMethodOrFile;
    private final int[] identifyByArgNumbers;
    private final int maxListElementsCached;
    private final boolean zip;

    public ValueForCacheMap(Method method, String dir, String nameMethodOrFile) {
        this.method = method;
        this.dir = dir;
        this.nameMethodOrFile = nameMethodOrFile;
        Cache cacheMethod = method.getAnnotation(Cache.class);
        this.maxListElementsCached = cacheMethod.maxListElementsCached();
        this.zip = cacheMethod.zip();
        if (method.getReturnType() == void.class) {
            throw new IllegalArgumentException("Данный метод не может вернуть результат из кэша, т.к. метод ничего не возвращает.");
        }
        if (method.getParameterCount() == 0) {
            throw new IllegalArgumentException("Данный метод не может вернуть результат из кэша, т.к. отсутствуют параметры для поиска в кэше.");
        }
        if (cacheMethod.identifyByArgNumbers().length == 0) {
            identifyByArgNumbers = new int[method.getParameterCount()];
            for (int i = 0; i < identifyByArgNumbers.length; i++) {
                identifyByArgNumbers[i] = i;
            }
        } else {
            identifyByArgNumbers = cacheMethod.identifyByArgNumbers();
        }
    }

    public Object getFromCache(Object[] args) {
        String key = getKey(args);
        return mapValue.get(key);

    }

    public Object putInCache(Object[] args, Object value) {
        if (value instanceof List) {
            List<Object> resultList = (List) value;
            if (resultList.size() > maxListElementsCached) {
                value = new ArrayList<>(resultList.subList(0, maxListElementsCached));
                for (int i = 0; i < ((ArrayList<?>) value).size(); i++) {
                    putInCache(args, ((ArrayList<?>) value).get(i));
                }
            }
        }
        String key = getKey(args);
        mapValue.put(key, value);
        return value;
    }

    public Object getFromFile(Object[] args) {
        String key = getKey(args);
        return getCacheFromFile(key);
    }

    public Object putInFile(Object[] args, Object value) {
        String key = getKey(args);
        putCacheInFile(key, value);
        return value;
    }

    private Object getCacheFromFile(String fileName) {
        Object object = null;
        try {
            ObjectInputStream inputStream = null;
            if (!zip) {
                FileInputStream fileInputStream = new FileInputStream(new File(dir, fileName));
                inputStream = new ObjectInputStream(fileInputStream);
            }
            else {
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(new File(dir, fileName)));
                zipInputStream.getNextEntry();
                inputStream = new ObjectInputStream(zipInputStream);
            }
            object = inputStream.readObject();
            inputStream.close();

        } catch (FileNotFoundException e) {
            throw new FileException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;

    }

    private void putCacheInFile(String fileName, Object value) {
        try {
            if (value instanceof List) {
                List<Object> resultList = (List) value;
                if (resultList.size() > maxListElementsCached) {
                    value = new ArrayList<>(resultList.subList(0, maxListElementsCached));
                    for (int i = 0; i < ((ArrayList<?>) value).size(); i++) {
                        putCacheInFile(fileName + i, ((ArrayList<?>) value).get(i));
                    }
                }
            }
            if (!zip) {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File(dir, fileName)));
                outputStream.writeObject(value);
                outputStream.close();
            }
            else {
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(dir, fileName)));
                zipOutputStream.putNextEntry(new ZipEntry(fileName));
                ObjectOutputStream outputStream = new ObjectOutputStream(zipOutputStream);
                outputStream.writeObject(value);
                zipOutputStream.close();
                outputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getKey(Object[] args) {
        StringBuilder stringBuilder = new StringBuilder(nameMethodOrFile);
        for (int i = 0; i < identifyByArgNumbers.length; i++) {
            stringBuilder.append(args[identifyByArgNumbers[i]].toString());
        }
        if(zip){
            stringBuilder.append(".zip");
        }
        return stringBuilder.toString();
    }
}
