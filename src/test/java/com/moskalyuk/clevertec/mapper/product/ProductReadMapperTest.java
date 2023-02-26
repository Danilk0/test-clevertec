package com.moskalyuk.clevertec.mapper.product;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardReadDto;
import com.moskalyuk.clevertec.dto.product.ProductReadDto;
import com.moskalyuk.clevertec.mapper.discountCard.DiscountCardReadMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductReadMapperTest {

    @Spy
    private ProductReadMapper readMapper;


    @Test
    void checkMappingFromDiscountCardToDiscountCardReadDto(){
        Product product= Product.newBuilder()
                .setId(2)
                .setPrice(BigDecimal.ZERO)
                .setName("test")
                .setPromotionalItem(false)
                .build();

        ProductReadDto readDto = readMapper.map(product);
        assertAll(
                ()->assertEquals(product.getId(),readDto.getId()),
                ()->assertEquals(product.getName(),readDto.getName()),
                ()->assertEquals(product.getPrice(),readDto.getPrice()),
                ()->assertEquals(product.getPromotionalItem(),readDto.getPromotionalItem())
        );

    }
}