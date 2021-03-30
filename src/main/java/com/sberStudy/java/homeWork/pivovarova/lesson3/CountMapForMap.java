package com.sberStudy.java.homeWork.pivovarova.lesson3;

import java.util.Map;

public class CountMapForMap<K> implements CountMap<K> {
    private Map<K, Integer> map;

    public CountMapForMap(Map<K, Integer> map) {
        this.map = map;
    }

//  добавляет элемент в этот контейнер.
    @Override
    public void add(K key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key)+1);
        }
        else {
            map.put(key, 1);
        }
    }

//  Возвращает количество добавлений данного элемента
    @Override
    public int getCount(K key) {
        return map.get(key);
    }

//  Удаляет элемент из контейнера и возвращает количество его добавлений(до удаления)
    @Override
    public int remove(K key) {
        return map.remove(key);
    }

//  количество разных элементов
    @Override
    public int size() {
        return map.size();
    }

//  Добавить все элементы из source в текущий контейнер, при совпадении ключей,     суммировать значения
    @Override
    public void addAll(CountMap<K> source) {
        for (K key : source.toMap().keySet()) {
            map.put(key, map.get(key) + source.getCount(key));
        }
    }

//  Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
    @Override
    public Map<K, Integer> toMap() {
        return map;
    }

//  Тот же самый контракт как и toMap(), только всю информацию записать в destination
    @Override
    public void toMap(Map<K, Integer> destination) {
        destination.putAll(map);
    }

}
