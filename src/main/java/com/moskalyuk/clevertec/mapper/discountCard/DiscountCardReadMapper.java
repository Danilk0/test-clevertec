package com.moskalyuk.clevertec.mapper.discountCard;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardReadDto;
import com.moskalyuk.clevertec.dto.product.ProductReadDto;
import com.moskalyuk.clevertec.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class DiscountCardReadMapper implements Mapper<DiscountCard, DiscountCardReadDto> {
    @Override
    public DiscountCardReadDto map(DiscountCard object) {
        return DiscountCardReadDto.newBuilder()
                .setId(object.getId())
                .setName(object.getName())
                .setBit(object.getBit())
                .build();
    }
}
