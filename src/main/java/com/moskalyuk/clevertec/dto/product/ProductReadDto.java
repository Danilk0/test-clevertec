package com.moskalyuk.clevertec.dto.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

public class ProductReadDto {
    @JsonSerialize
    private Integer id;
    @JsonSerialize
    private String name;
    @JsonSerialize
    private BigDecimal price;
    @JsonSerialize
    private Boolean promotionalItem;

    private ProductReadDto() {
    }

    public static ProductReadDto.Builder newBuilder() {
        return new ProductReadDto().new Builder();
    }

    public class Builder{
        private Builder() {
        }
        public Builder setId(Integer id) {
            ProductReadDto.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            ProductReadDto.this.name = name;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            ProductReadDto.this.price = price;
            return this;
        }

        public Builder setPromotionalItem(Boolean promotionalItem) {
            ProductReadDto.this.promotionalItem = promotionalItem;
            return this;
        }

        public ProductReadDto build() {
            return ProductReadDto.this;
        }

    }




}
