package com.moskalyuk.clevertec.caches;

import com.moskalyuk.clevertec.database.entity.BaseEntity;
import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LRUCacheTest {

    @Mock
    LRUCache<BaseEntity> cache;

    private final DiscountCard DISCOUNT_CARD = DiscountCard.newBuilder()
            .setName("Test")
            .setBit(1)
            .setId(1)
            .build();

    private final Product PRODUCT = Product.newBuilder()
            .setId(1)
            .setPromotionalItem(false)
            .setPrice(BigDecimal.ONE)
            .setName("Test")
            .build();


    @Nested
    class checkingGetMethodInLFUCache{
        @Test
        public void shouldReturnDiscountCardFromCache(){
            when(cache.get(anyInt())).thenReturn(Optional.of(DISCOUNT_CARD));

            Optional<BaseEntity> actual = cache.get(1);

            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(DISCOUNT_CARD);
            verify(cache, times(1)).get(anyInt());
            verifyNoMoreInteractions(cache);
        }
        @Test
        public void shouldReturnProductFromCache(){
            when(cache.get(anyInt())).thenReturn(Optional.of(PRODUCT));

            Optional<BaseEntity> actual = cache.get(1);

            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(PRODUCT);
            verify(cache, times(1)).get(anyInt());
            verifyNoMoreInteractions(cache);
        }
    }

    @Nested
    class checkingSetMethodInLFUCache{
        @ParameterizedTest
        @ValueSource(ints = {1,2})
        public void shouldSetDiscountCardInCache(int id){
            doNothing().when(cache).set(anyInt(), any(BaseEntity.class));

            cache.set(id,DISCOUNT_CARD);

            verify(cache, times(1)).set(id,DISCOUNT_CARD);
            verifyNoMoreInteractions(cache);
        }
        @ParameterizedTest
        @ValueSource(ints = {1,2})
        public void shouldSetProductInCache(int id){
            doNothing().when(cache).set(anyInt(), any(BaseEntity.class));

            cache.set(id,PRODUCT);

            verify(cache, times(1)).set(id,PRODUCT);
            verifyNoMoreInteractions(cache);
        }
    }

    @Nested
    class checkingDeleteMethodInLFUCache{
        @ParameterizedTest
        @ValueSource(ints = {1,2})
        public void shouldDeleteDiscountCardInCache(int id){
            doNothing().when(cache).delete(anyInt());

            cache.delete(id);

            verify(cache, times(1)).delete(id);
            verifyNoMoreInteractions(cache);
        }

        @ParameterizedTest
        @ValueSource(ints = {1,2})
        public void shouldDeleteProductInCache(int id){
            doNothing().when(cache).delete(anyInt());

            cache.delete(id);

            verify(cache, times(1)).delete(id);
            verifyNoMoreInteractions(cache);
        }
    }


}