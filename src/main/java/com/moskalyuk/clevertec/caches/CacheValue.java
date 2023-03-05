package com.moskalyuk.clevertec.caches;

public class CacheValue<V> {
    Integer key;

    V value;

    CacheValue(Integer key, V value) {
        this.key = key;
        this.value = value;
    }

}
