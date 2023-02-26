package com.moskalyuk.clevertec.service;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.database.repository.DiscountCardRepository;
import com.moskalyuk.clevertec.database.repository.ProductRepository;
import com.moskalyuk.clevertec.util.BaseClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckServiceTest{


    @InjectMocks
    private CheckService service;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private DiscountCardRepository discountCardRepository;

    @ParameterizedTest
    @ValueSource(strings = {"test"})
    public void shouldReturnCheck(String cardNumber){
        List<Integer> ids = List.of(1,2,4);

        final Product expectedProduct = Product.newBuilder()
                .setId(1)
                .setName("test")
                .setPrice(BigDecimal.ONE)
                .setPromotionalItem(true)
                .build();

        final DiscountCard expectedDiscountCard = DiscountCard.newBuilder()
                .setId(1)
                .setBit(1)
                .setName("test")
                .build();
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(expectedProduct));
        when(discountCardRepository.findByName(anyString())).thenReturn(Optional.of(expectedDiscountCard));

        var actual = service.getFile(ids, cardNumber);

        assertNotNull(actual);
    }


}