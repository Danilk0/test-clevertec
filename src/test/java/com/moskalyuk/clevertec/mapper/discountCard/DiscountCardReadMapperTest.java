package com.moskalyuk.clevertec.mapper.discountCard;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardReadDto;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DiscountCardReadMapperTest {

    @Spy
    private DiscountCardReadMapper readMapper;


    @Test
    void checkMappingFromDiscountCardToDiscountCardReadDto(){
        DiscountCard discountCard= DiscountCard.newBuilder()
                .setId(2)
                .setBit(1)
                .setName("Test")
                .build();

        DiscountCardReadDto readDto = readMapper.map(discountCard);
        assertAll(
                ()->assertEquals(discountCard.getBit(),readDto.getBit()),
                ()->assertEquals(discountCard.getName(),readDto.getName()),
                ()->assertEquals(discountCard.getId(),readDto.getId())
        );

    }

}