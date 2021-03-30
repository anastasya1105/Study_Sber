package com.sberStudy.java.homeWork.pivovarova.lesson3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }
    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }
    public static <T> int indexOf(List<? extends T> source, T someObj) {
        return source.indexOf(someObj);
    }
    public static <T> List<? super T> limit(List<? extends T> source, int size) {
        return new ArrayList<>(source.subList(0, size));
    }
    public static <T> void add(List<T> source, T someObj) {
        source.add(someObj);
    }
    public static <T> T get(List<? extends T> source, int index) {
        return source.get(index);
    }

    public static <T> void removeAll(List<? extends T> removeFrom, List<T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<Integer> c1, List<Number> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T t : c2) {
            if (c1.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable<? super T>> List<T> range(List<T> list, T min, T max) {
        List<T> resultList = newArrayList();

        for (T t : list) {
            if (t.compareTo(min) >= 0 && t.compareTo(max) <= 0) {
                resultList.add(t);
            }
        }

        return resultList;
    }

    public static <T extends Comparable<? super T>> List<T> range(List<T> list, T min, T max, Comparator< T> comparator) {

        List<T> resultList = newArrayList();

        for (T t : list) {
            if (comparator.compare(t, min) >= 0 && comparator.compare(t, max) <= 0) {
                resultList.add(t);
            }
        }

        return resultList;
    }

}
