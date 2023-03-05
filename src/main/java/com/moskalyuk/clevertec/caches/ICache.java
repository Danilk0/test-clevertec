package com.moskalyuk.clevertec.caches;

import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

public interface ICache<K,V> {

    Optional<V> get(K key);

    void set(K key, V value);

    void delete(K key);
}
