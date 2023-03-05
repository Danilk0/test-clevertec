package com.moskalyuk.clevertec.database.repository;

import com.moskalyuk.clevertec.caches.annotation.DeleteCache;
import com.moskalyuk.clevertec.caches.annotation.GetCache;
import com.moskalyuk.clevertec.caches.annotation.PostCache;
import com.moskalyuk.clevertec.caches.annotation.UpdateCache;
import com.moskalyuk.clevertec.database.entity.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountCardRepository extends CrudRepository<DiscountCard,Integer>,JpaRepository<DiscountCard,Integer>{
    List<DiscountCard> findAll();

    @GetCache(DiscountCard.class)
    Optional<DiscountCard> findById(Integer id);

    @PostCache(DiscountCard.class)
    DiscountCard save(DiscountCard discountCard);

    @UpdateCache(DiscountCard.class)
    DiscountCard saveAndFlush(DiscountCard discountCard);

    @DeleteCache(DiscountCard.class)
    void delete(DiscountCard discountCard);

    Optional<DiscountCard> findByName(String name);

}
