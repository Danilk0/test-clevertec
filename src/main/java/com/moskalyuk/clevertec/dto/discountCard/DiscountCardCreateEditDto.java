package com.moskalyuk.clevertec.dto.discountCard;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;
import com.moskalyuk.clevertec.dto.Check;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.Objects;

public class DiscountCardCreateEditDto {
    private String name;
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
