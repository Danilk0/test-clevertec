package com.moskalyuk.clevertec.database.repository;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>,JpaRepository<Product,Integer> {

    List<Product> findAll();


    Optional<Product> findById(Integer id);
    Optional<Product> findByName(String name);
}
