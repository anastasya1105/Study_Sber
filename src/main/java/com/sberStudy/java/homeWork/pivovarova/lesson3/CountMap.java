package com.sberStudy.java.homeWork.pivovarova.lesson3;

import java.util.Map;

public interface CountMap<K> {
    void add(K key);

    int getCount(K key);

    int remove(K key);

    int size();

    void addAll(CountMap<K> source);

    Map<K, Integer> toMap();

    void toMap(Map<K, Integer> destination);

//    public static void main(String[] args) {
//        CountMap map = null;
//
//        map.add(10);
//        map.add(10);
//        map.add(5);
//        map.add(6);
//        map.add(5);
//        map.add(10);
//    }
}
