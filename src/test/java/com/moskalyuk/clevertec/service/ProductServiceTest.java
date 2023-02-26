package com.moskalyuk.clevertec.service;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.database.repository.DiscountCardRepository;
import com.moskalyuk.clevertec.database.repository.ProductRepository;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardReadDto;
import com.moskalyuk.clevertec.dto.product.ProductCreateEditDto;
import com.moskalyuk.clevertec.dto.product.ProductReadDto;
import com.moskalyuk.clevertec.mapper.discountCard.DiscountCardCreateEditMapper;
import com.moskalyuk.clevertec.mapper.discountCard.DiscountCardReadMapper;
import com.moskalyuk.clevertec.mapper.product.ProductCreateEditMapper;
import com.moskalyuk.clevertec.mapper.product.ProductReadMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private  ProductService service;
    @Mock
    private ProductRepository repository;

    @Spy
    private ProductReadMapper readMapper;

    @Spy
    private ProductCreateEditMapper createEditMapper;

    @Test
    void shouldFindAndReturnAllProducts(){
        when(repository.findAll()).thenReturn(List.of(new Product(), new Product()));

        assertThat(service.findAll()).hasSize(2);
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,5,6})
    void shouldFindAndReturnOneProduct(Integer randomInt){
        final Product expectedProduct = Product.newBuilder()
                        .setId(1)
                        .setName("test")
                        .setPrice(BigDecimal.ONE)
                        .setPromotionalItem(true)
                        .build();
        when(repository.findById(anyInt())).thenReturn(Optional.of(expectedProduct));

        final Optional<ProductReadDto> actual = service.findById(randomInt);

        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(expectedProduct);
        verify(repository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(repository);
    }


    @Test
    void shouldSaveOneDiscountCard(){
        final Product productToSave = Product.newBuilder()
                .setId(1)
                .setName("test")
                .setPrice(BigDecimal.ONE)
                .setPromotionalItem(true)
                .build();
        when(repository.save(any(Product.class))).thenReturn(productToSave);

        ProductReadDto actual = service.create(ProductCreateEditDto.newBuilder().build());

        assertThat(actual).usingRecursiveComparison().isEqualTo(readMapper.map(productToSave));
        verify(repository, times(1)).save(any(Product.class));
        verifyNoMoreInteractions(repository);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,5,6})
    void shouldEditAndSaveOneDiscountCard(int randomInt){
        final Product productToUpd = Product.newBuilder()
                .setId(1)
                .setName("test")
                .setPrice(BigDecimal.ONE)
                .setPromotionalItem(true)
                .build();
        when(repository.findById(anyInt())).thenReturn(Optional.of(productToUpd));
        when(repository.saveAndFlush(any(Product.class))).thenReturn(productToUpd);

        Optional<ProductReadDto> actual = service.update(randomInt,ProductCreateEditDto.newBuilder().build());

        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(readMapper.map(productToUpd));
        verify(repository, times(1)).saveAndFlush(any(Product.class));
        verifyNoMoreInteractions(repository);

    }

    @ParameterizedTest
    @ValueSource(ints = {1,5,6})
    void shouldDeleteOneDiscountCard(int randomInt) {
        final Product expectedProduct = Product.newBuilder()
                .setId(1)
                .setName("test")
                .setPrice(BigDecimal.ONE)
                .setPromotionalItem(true)
                .build();
        when(repository.findById(anyInt())).thenReturn(Optional.of(expectedProduct));
        doNothing().when(repository).flush();
        doNothing().when(repository).delete(any(Product.class));

        service.delete(randomInt);
        verify(repository, times(1)).delete(any(Product.class));
        verifyNoMoreInteractions(repository);
    }

}