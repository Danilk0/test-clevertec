package com.moskalyuk.clevertec.caches;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("LRU")
public class LRUCache<V> implements ICache<Integer, V> {
    @Value("${cache.capacity}")
    private int CACHE_CAPACITY;

    private final List<Integer> cachePriorityQueue = new LinkedList<>();
    private final Map<Integer, CacheValue<V>> cacheMap = new HashMap<>();

    @Override
    public Optional<V> get(Integer key)
    {
        if(cacheMap.containsKey(key))
        {
            CacheValue<V> current = cacheMap.get(key);
            cachePriorityQueue.remove(current.key);
            cachePriorityQueue.add(0,current.key);
            return Optional.of(current.value);
        }
        return Optional.empty();
    }
    @Override
    public void set(Integer key, V value)
    {
        if(cacheMap.containsKey(key))
        {
            CacheValue<V> curr = cacheMap.get(key);
            cachePriorityQueue.remove(curr.key);
        }
        else
        {
            if(cachePriorityQueue.size() == CACHE_CAPACITY)
            {
                Integer temp = cachePriorityQueue.remove(cachePriorityQueue.size()-1);
                cacheMap.remove(temp);
            }
        }
        CacheValue<V> newItem = new CacheValue<>(key, value);
        cachePriorityQueue.add(0,newItem.key);
        cacheMap.put(key, newItem);
    }

    @Override
    public void delete(Integer key) {
        cacheMap.remove(key);
        cachePriorityQueue.remove(key);
    }

}
