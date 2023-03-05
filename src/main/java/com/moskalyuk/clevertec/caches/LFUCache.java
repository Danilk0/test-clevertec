package com.moskalyuk.clevertec.caches;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("LFU")
public class LFUCache<V> implements ICache<Integer,V> {
    @Value("${cache.capacity}")
    private int CACHE_CAPACITY;

    private final Map<Integer, CacheValue<V>> cacheMap = new HashMap<>();
    private final Map<Integer, Integer> countCacheMap = new HashMap<>();


    @Override
    public Optional<V> get(Integer key) {
        if(cacheMap.containsKey(key)){
            CacheValue<V> current = cacheMap.get(key);
            countCacheMap.put(key,countCacheMap.get(key)+1);
            return Optional.of(current.value);
        }
        return Optional.empty();
    }

    @Override
    public void set(Integer key, V value) {
        int index=0;
        if(cacheMap.containsKey(key)) {
            cacheMap.remove(key);
            index = countCacheMap.get(key);
        }
        else{
            if(cacheMap.size() == CACHE_CAPACITY){
                Integer minKey = keyOfMinValue();
                countCacheMap.remove(minKey);
                cacheMap.remove(minKey);
            }
        }

        CacheValue<V> newItem = new CacheValue<>(key, value);
        cacheMap.put(key,newItem);
        countCacheMap.put(key,index+1);

    }

    @Override
    public void delete(Integer key) {
        cacheMap.remove(key);
        countCacheMap.remove(key);
    }


    private Integer keyOfMinValue(){
        Optional<Integer> min = countCacheMap.values().stream()
                .min(Comparator.comparingInt(Integer::intValue));
        Optional<Integer> collect = countCacheMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(min.get()))
                .map(Map.Entry::getKey)
                .findFirst();
        return collect.get();
    }
}
