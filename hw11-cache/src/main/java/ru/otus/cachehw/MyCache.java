package ru.otus.cachehw;


import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы

    private final ArrayList<HwListener> listeners = new ArrayList<>();
    private final Map<K, V> cache = new WeakHashMap<>();

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
        listeners.forEach(hwListener -> hwListener.notify(key, value, "put"));
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
        listeners.forEach(hwListener -> hwListener.notify(key, null, "remove"));
    }

    @Override
    public V get(K key) {
        V value = cache.get(key);
        listeners.forEach(hwListener -> hwListener.notify(key, value, "get"));
        return value;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }
}
