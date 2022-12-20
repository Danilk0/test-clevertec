package com.moskalyuk.clevertec.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import com.moskalyuk.clevertec.validation.UniqueProductName;


import java.math.BigDecimal;

public class ProductCreateEditDto {
    @NotNull(message = "Product name not valid")
    @Size(min=2,max = 64,message = "Product name don't have invalid size")
    @UniqueProductName()
    private String name;

    @PositiveOrZero(message = "Product don't have invalid price")
    private BigDecimal price;
    private Boolean promotionalItem;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getPromotionalItem() {
        return promotionalItem;
    }

    private ProductCreateEditDto() {
    }

    public static ProductCreateEditDto.Builder newBuilder() {
        return new ProductCreateEditDto().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public ProductCreateEditDto.Builder setName(String name) {
            ProductCreateEditDto.this.name = name;
            return this;
        }

        public ProductCreateEditDto.Builder setPrice(BigDecimal price) {
            ProductCreateEditDto.this.price = price;
            return this;
        }

        public ProductCreateEditDto.Builder setPromotionalItem(Boolean promotionalItem) {
            ProductCreateEditDto.this.promotionalItem = promotionalItem;
            return this;
        }

        public ProductCreateEditDto build() {
            return ProductCreateEditDto.this;
        }
    }

}
