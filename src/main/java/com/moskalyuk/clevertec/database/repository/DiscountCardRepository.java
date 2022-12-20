package com.moskalyuk.clevertec.database.repository;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountCardRepository extends CrudRepository<DiscountCard,Integer>,JpaRepository<DiscountCard,Integer>{

    List<DiscountCard> findAll();

    Optional<DiscountCard> findByName(String name);

}
