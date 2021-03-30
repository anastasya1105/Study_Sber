package com.sberStudy.java.homeWork.pivovarova.lesson4;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyIterable<T> implements Iterable{

    private T[] objArr;

    public MyIterable(T[] objArr) {
        this.objArr = objArr;
    }
    @Override
    public Iterator iterator() {

        return new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < objArr.length && objArr[currentIndex] != null;
            }

            @Override
            public T next() {
                return objArr[currentIndex++];
            }
        };
    }

}
