package com.moskalyuk.clevertec.database.entity;


import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Table(name = "discount_card")
@Entity
public class DiscountCard extends BaseEntity<Integer> {
    @Column
    private String name;

    @Column
    private Integer bit;

    public String getName() {
        return name;
    }

    public Integer getBit() {
        return bit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBit(Integer bit) {
        this.bit = bit;
    }

    public DiscountCard() {
    }

    public static DiscountCard.Builder newBuilder() {
        return new DiscountCard().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public DiscountCard.Builder setId(Integer id){
            DiscountCard.this.id = id;
            return this;
        }
        public DiscountCard.Builder setName(String name){
            DiscountCard.this.name = name;
            return this;
        }
        public DiscountCard.Builder setBit(Integer bit){
            DiscountCard.this.bit = bit;
            return this;
        }

        public DiscountCard build() {
            return DiscountCard.this;
        }
    }

}
