package com.moskalyuk.clevertec.mapper.discountCard;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import com.moskalyuk.clevertec.dto.product.ProductCreateEditDto;
import com.moskalyuk.clevertec.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class DiscountCardCreateEditMapper implements Mapper<DiscountCardCreateEditDto, DiscountCard> {

    @Override
    public DiscountCard map(DiscountCardCreateEditDto object) {
        return DiscountCard.newBuilder()
                .setName(object.getName())
                .setBit(object.getBit())
                .build();
    }

    @Override
    public DiscountCard map(DiscountCardCreateEditDto fromObject, DiscountCard toObject) {
        toObject.setName(fromObject.getName());
        toObject.setBit(fromObject.getBit());
        return toObject;
    }
}