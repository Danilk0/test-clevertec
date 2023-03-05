package com.moskalyuk.clevertec.database.repository;

import com.moskalyuk.clevertec.caches.annotation.DeleteCache;
import com.moskalyuk.clevertec.caches.annotation.GetCache;
import com.moskalyuk.clevertec.caches.annotation.PostCache;
import com.moskalyuk.clevertec.caches.annotation.UpdateCache;
import com.moskalyuk.clevertec.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>,JpaRepository<Product,Integer> {

    List<Product> findAll();

    @GetCache(Product.class)
    Optional<Product> findById(Integer id);

    @PostCache(Product.class)
    Product save(Product product);

    @DeleteCache(Product.class)
    void delete(Product product);

    @UpdateCache(Product.class)
    Product saveAndFlush(Product discountCard);

    Optional<Product> findByName(String name);
}
