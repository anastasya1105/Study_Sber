package com.sberStudy.java.homeWork.pivovarova.lesson9;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Streams<T> {

    private List<T> value;

    public Streams(Collection<T> collection) {
        this.value = new ArrayList<>(collection);
    }

    public static <T> Streams<T> of(Collection<T> collection) {
        return new Streams(collection);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        boolean result = value.removeIf(predicate);
        return this;
    }

    public<R> Streams<R> transform (Function<? super T, ? extends R> transformFunction) {
        List<R> transformList = new ArrayList<>();
        value.forEach(t -> transformList.add(transformFunction.apply(t)));
        return new Streams<R>(transformList);
    }

    public <K, V>  Map<K, V> toMap(Function<? super T, ? extends K> functionKey, Function<? super T, ? extends V> functionValue) {
        Map<K, V> streamsMap = new HashMap<>();
        value.forEach(t -> streamsMap.put(functionKey.apply(t), functionValue.apply(t)));
        return streamsMap;
    }


}
