package com.moskalyuk.clevertec.service;

import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.database.repository.DiscountCardRepository;
import com.moskalyuk.clevertec.database.repository.ProductRepository;
import com.moskalyuk.clevertec.dto.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CheckService {

    private final DiscountCardRepository cardRepository;

    private final ProductRepository productRepository;

    @Autowired
    public CheckService(DiscountCardRepository cardRepository, ProductRepository productRepository) {
        this.cardRepository = cardRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Map<String, BigDecimal> getCheck(List<Integer> ids, String cardNumber){
        Map<Product,Integer> products=new HashMap<>();
        for (Integer id : ids) {
            Optional<Product> product = productRepository.findById(id);
            if(product.isPresent() && products.containsKey(product.get())){
                products.put(product.get(),(products.get(product.get())+1));
            } else product.ifPresent(value -> products.put(value, 1));
        }
        return Check.newBuilder()
                .setProducts(products)
                .setDiscountCard(cardRepository.findByName(cardNumber).get())
                .build()
                .getCheck();
    }


}
