package com.moskalyuk.clevertec.mapper.product;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import com.moskalyuk.clevertec.dto.product.ProductCreateEditDto;
import com.moskalyuk.clevertec.mapper.discountCard.DiscountCardCreateEditMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductCreateEditMapperTest {

    @Spy
    private ProductCreateEditMapper createEditMapper;

    @Test
    void checkMappingFromProductCreateEditDtoToProduct() {
        ProductCreateEditDto createEditDto = ProductCreateEditDto.newBuilder()
                .setPrice(BigDecimal.ZERO)
                .setName("test")
                .setPromotionalItem(false)
                .build();

        Product product=  createEditMapper.map(createEditDto);
        assertAll(
                ()->assertEquals(product.getName(),createEditDto.getName()),
                ()->assertEquals(product.getPrice(),createEditDto.getPrice()),
                ()->assertEquals(product.getPromotionalItem(),createEditDto.getPromotionalItem())
        );
    }

}