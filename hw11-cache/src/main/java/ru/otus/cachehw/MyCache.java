package ru.otus.cachehw;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
    //Надо реализовать эти методы
    private static final String PUT = "put";
    private static final String GET = "get";
    private static final String REMOVE = "remove";
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);

    private final List<HwListener> listeners = new ArrayList<>();
    private final Map<K, V> cache = new WeakHashMap<>();

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
        notifyListener(key, value, PUT);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
        notifyListener(key, null, REMOVE);
    }

    @Override
    public V get(K key) {
        V value = cache.get(key);
        notifyListener(key, value, GET);
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

    private void notifyListener(K key, V value, String action) {
        listeners.forEach(listener -> {
            try {
                listener.notify(key, value, action);
            } catch (Throwable e) {
                logger.error("Notify error listener:" + listener.toString(), e);
            }
        });
    }
}
