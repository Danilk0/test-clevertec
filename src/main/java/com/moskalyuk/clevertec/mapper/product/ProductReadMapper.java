package com.moskalyuk.clevertec.mapper.product;

import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.dto.product.ProductReadDto;
import com.moskalyuk.clevertec.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductReadMapper implements Mapper<Product, ProductReadDto> {
    @Override
    public ProductReadDto map(Product object) {
        return ProductReadDto.newBuilder()
                .setId(object.getId())
                .setName(object.getName())
                .setPrice(object.getPrice())
                .setPromotionalItem(object.getPromotionalItem())
                .build();
    }
}
