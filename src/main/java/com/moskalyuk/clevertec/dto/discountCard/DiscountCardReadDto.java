package com.moskalyuk.clevertec.dto.discountCard;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Objects;

public class DiscountCardReadDto {
    @JsonSerialize
    private Integer id;
    @JsonSerialize
    private String name;
    @JsonSerialize
    private Integer bit;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBit() {
        return bit;
    }

    public DiscountCardReadDto() {
    }

    public static DiscountCardReadDto.Builder newBuilder() {
        return new DiscountCardReadDto().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public DiscountCardReadDto.Builder setId(Integer id){
            DiscountCardReadDto.this.id = id;
            return this;
        }

        public DiscountCardReadDto.Builder setName(String name){
            DiscountCardReadDto.this.name = name;
            return this;
        }
        public DiscountCardReadDto.Builder setBit(Integer bit){
            DiscountCardReadDto.this.bit = bit;
            return this;
        }

        public DiscountCardReadDto build() {
            return DiscountCardReadDto.this;
        }
    }
}
