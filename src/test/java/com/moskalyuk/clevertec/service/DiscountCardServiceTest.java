package com.moskalyuk.clevertec.service;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.repository.DiscountCardRepository;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardReadDto;
import com.moskalyuk.clevertec.mapper.discountCard.DiscountCardCreateEditMapper;
import com.moskalyuk.clevertec.mapper.discountCard.DiscountCardReadMapper;
import com.moskalyuk.clevertec.util.BaseClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DiscountCardServiceTest  {

    @InjectMocks
    private  DiscountCardService service;
    @Mock
    private DiscountCardRepository repository;

    @Spy
    private DiscountCardReadMapper readMapper;

    @Spy
    private DiscountCardCreateEditMapper createEditMapper;

    @Test
    void shouldFindAndReturnAllDiscountCards(){
        when(repository.findAll()).thenReturn(List.of(new DiscountCard(), new DiscountCard()));

        assertThat(service.findAll()).hasSize(2);
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,5,6})
    void shouldFindAndReturnOneDiscountCards(Integer randomInt){
        final DiscountCard expectedDiscountCard = DiscountCard.newBuilder()
                                                .setId(1)
                                                .setBit(1)
                                                .setName("test")
                                                .build();
        when(repository.findById(anyInt())).thenReturn(Optional.of(expectedDiscountCard));

        final Optional<DiscountCardReadDto> actual = service.findById(randomInt);

       assertThat(actual.get()).usingRecursiveComparison().isEqualTo(expectedDiscountCard);
        verify(repository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(repository);
    }


    @Test
    void shouldSaveOneDiscountCard(){
        final DiscountCard discountCardToSave = DiscountCard.newBuilder()
                .setId(1)
                .setBit(1)
                .setName("test")
                .build();
        when(repository.save(any(DiscountCard.class))).thenReturn(discountCardToSave);

        DiscountCardReadDto actual = service.create(DiscountCardCreateEditDto.newBuilder().build());

        assertThat(actual).usingRecursiveComparison().isEqualTo(readMapper.map(discountCardToSave));
        verify(repository, times(1)).save(any(DiscountCard.class));
        verifyNoMoreInteractions(repository);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,5,6})
    void shouldEditAndSaveOneDiscountCard(int randomInt){
        final DiscountCard discountCardToUpd = DiscountCard.newBuilder()
                .setId(1)
                .setBit(1)
                .setName("test")
                .build();
        when(repository.findById(anyInt())).thenReturn(Optional.of(discountCardToUpd));
        when(repository.saveAndFlush(any(DiscountCard.class))).thenReturn(discountCardToUpd);

        Optional<DiscountCardReadDto> actual = service.update(randomInt,DiscountCardCreateEditDto.newBuilder().build());

        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(readMapper.map(discountCardToUpd));
        verify(repository, times(1)).saveAndFlush(any(DiscountCard.class));
        verifyNoMoreInteractions(repository);

    }

    @ParameterizedTest
    @ValueSource(ints = {1,5,6})
    void shouldDeleteOneDiscountCard(int randomInt) {
        final DiscountCard expectedDiscountCard = DiscountCard.newBuilder()
                .setId(1)
                .setBit(1)
                .setName("test")
                .build();
        when(repository.findById(anyInt())).thenReturn(Optional.of(expectedDiscountCard));
        doNothing().when(repository).flush();
        doNothing().when(repository).delete(any(DiscountCard.class));

        service.delete(randomInt);
        verify(repository, times(1)).delete(any(DiscountCard.class));
        verifyNoMoreInteractions(repository);
    }

}