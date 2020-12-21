package study.lesson3.generics;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<K> implements CountMap<K> {

    private Map<K, Integer> map;

    public CountMapImpl() {
        map = new HashMap<>();
    }
    @Override
    public void add(K key) {

        Integer objCount = map.get(key);
        if (objCount != null) {
            map.put(key, ++objCount);

        } else {
            map.put(key, 1);
        }
    }
    @Override
    public int getCount(K key) {

        Integer result = map.get(key);
        return result != null ? result : 0;
    }

    @Override
    public int remove(K key) {

        Integer objCount = map.remove(key);
        return objCount != null ? objCount : 0;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<K> source) {
        for (K key : source.toMap().keySet()) {
            map.put(key, map.get(key) + source.getCount(key));
        }
    }

    @Override
    public Map<K, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<K, Integer> destination) {
        destination.putAll(map);

    }
}
