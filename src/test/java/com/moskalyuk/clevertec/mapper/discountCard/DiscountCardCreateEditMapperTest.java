package com.moskalyuk.clevertec.mapper.discountCard;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DiscountCardCreateEditMapperTest {

    @Spy
    private  DiscountCardCreateEditMapper createEditMapper;

    @Test
    void checkMappingFromDiscountCardCreateEditDtoToDiscountCard() {
        DiscountCardCreateEditDto createEditDto = DiscountCardCreateEditDto.newBuilder()
                .setBit(1)
                .setName("test")
                .build();

        DiscountCard discountCard=  createEditMapper.map(createEditDto);
        assertAll(
                ()->assertEquals(discountCard.getBit(),createEditDto.getBit()),
                ()->assertEquals(discountCard.getName(),createEditDto.getName())
        );
    }
}