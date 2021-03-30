package com.sberStudy.java.homeWork.pivovarova.lesson3;

import java.util.*;

public class CountMapForList<K> implements CountMap<K> {
    private final List<K> list;

    public List<K> getList() {
        return list;
    }
    public CountMapForList(List<K> list) {
        this.list = list;
    }

    public CountMapForList() {
        this.list = new ArrayList<>();
    }

//  добавляет элемент в этот контейнер.
    @Override
    public void add(K key) {
        list.add(key);
    }

//  Возвращает количество добавлений данного элемента
    @Override
    public int getCount(K key) {
        return (int) list.stream().filter(t -> t.equals(key)).count();
    }

//  Удаляет элемент из контейнера и возвращает количество его добавлений(до удаления)
    @Override
    public int remove(K key) {
        int count = getCount(key);
        list.remove(key);
        return count;
    }

//  количество разных элементов
    @Override
    public int size() {
        return (int) list.stream().distinct().count();
    }

//  Добавить все элементы из source в текущий контейнер, при совпадении ключей,     суммировать значения
    @Override
    public void addAll(CountMap<K> source) {
        Iterator<K> iterator = ((CountMapForList<K>) source).list.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
    }

//  Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
    @Override
    public Map<K, Integer> toMap() {
        Map<K, Integer> map = new HashMap<>();
        for (K element : list) {
            map.put(element, getCount(element));
        }
        return map;
    }

//  Тот же самый контракт как и toMap(), только всю информацию записать в destination
    @Override
    public void toMap(Map<K, Integer> destination) {
        for (K element : list) {
            destination.put(element, getCount(element));
        }
    }
}
