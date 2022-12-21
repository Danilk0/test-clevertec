package com.moskalyuk.clevertec.dto.discountCard;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.dto.Check;
import com.moskalyuk.clevertec.validation.UniqueCardName;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Map;
import java.util.Objects;

public class DiscountCardCreateEditDto {
    @NotNull(message = "Discount card name not valid")
    @Size(min=2,max = 64,message = "Discount card name don't have invalid size")
    @UniqueCardName()
    private String name;
    @Positive(message = "Bit not valid")
    private Integer bit;

    private DiscountCardCreateEditDto() {
    }

    public String getName() {
        return name;
    }

    public Integer getBit() {
        return bit;
    }

    public static DiscountCardCreateEditDto.Builder newBuilder() {
        return new DiscountCardCreateEditDto().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public DiscountCardCreateEditDto.Builder setName(String name){
            DiscountCardCreateEditDto.this.name = name;
            return this;
        }
        public DiscountCardCreateEditDto.Builder setBit(Integer bit){
            DiscountCardCreateEditDto.this.bit = bit;
            return this;
        }

        public DiscountCardCreateEditDto build() {
            return DiscountCardCreateEditDto.this;
        }
    }

}
