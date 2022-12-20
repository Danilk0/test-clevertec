package com.moskalyuk.clevertec.database.entity;

import com.moskalyuk.clevertec.dto.Check;
import com.moskalyuk.clevertec.dto.product.ProductCreateEditDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Map;

@Table(name = "product")
@Entity
public class Product extends BaseEntity<Integer>{

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column(name = "promotional_item")
    private Boolean promotionalItem;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getPromotionalItem() {
        return promotionalItem;
    }

    public void setPromotionalItem(Boolean promotionalItem) {
        this.promotionalItem = promotionalItem;
    }

    public static Product.Builder newBuilder() {
        return new Product().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Product.Builder setId(Integer id) {
            Product.this.id = id;
            return this;
        }
        public Product.Builder setName(String name) {
            Product.this.name = name;
            return this;
        }

        public Product.Builder setPrice(BigDecimal price) {
            Product.this.price = price;
            return this;
        }

        public Product.Builder setPromotionalItem(Boolean promotionalItem) {
            Product.this.promotionalItem = promotionalItem;
            return this;
        }

        public Product build() {
            return Product.this;
        }
    }
}
