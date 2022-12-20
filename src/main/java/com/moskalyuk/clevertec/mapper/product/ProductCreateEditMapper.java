package com.moskalyuk.clevertec.mapper.product;

import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.dto.product.ProductCreateEditDto;
import com.moskalyuk.clevertec.dto.product.ProductReadDto;
import com.moskalyuk.clevertec.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateEditMapper implements Mapper<ProductCreateEditDto, Product> {

    @Override
    public Product map(ProductCreateEditDto object) {
        return Product.newBuilder()
                .setName(object.getName())
                .setPrice(object.getPrice())
                .setPromotionalItem(object.getPromotionalItem())
                .build();
    }

    @Override
    public Product map(ProductCreateEditDto fromObject, Product toObject) {
        toObject.setName(fromObject.getName());
        toObject.setPrice(fromObject.getPrice());
        toObject.setPromotionalItem(fromObject.getPromotionalItem());
        return toObject;
    }
}