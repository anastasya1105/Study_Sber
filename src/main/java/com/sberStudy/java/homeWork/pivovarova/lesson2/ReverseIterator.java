package com.sberStudy.java.homeWork.pivovarova.lesson2;

import java.util.Iterator;
import java.util.List;

public class ReverseIterator<Car> implements Iterator<Car>, Iterable<Car>{
    private final List<Car> list;
    private int position;

    public ReverseIterator(List<Car> list) {
        this.list = list;
        this.position = list.size() - 1;
    }

    @Override
    public Iterator<Car> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public Car next() {
        return list.get(position--);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
