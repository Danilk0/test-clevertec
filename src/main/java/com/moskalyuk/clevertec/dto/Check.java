package com.moskalyuk.clevertec.dto;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Check {
    private Check(){}
    private Map<Product, Integer> products;
    private DiscountCard discountCard;
    private BigDecimal sum = BigDecimal.ZERO;

    public Map<String,BigDecimal> getCheck(){
        Map<String,BigDecimal> check = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            BigDecimal price=(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
            if(entry.getValue()>=5 && entry.getKey().getPromotionalItem()){
                sum=sum.add(price.multiply(BigDecimal.valueOf(0.9)));
                check.put(
                        entry.getKey().getName(),
                        price.multiply(BigDecimal.valueOf(0.9)));
                continue;
            }
            sum=sum.add(price);
            check.put(
                    entry.getKey().getName(),
                    price);
        }
        if(discountCard!=null){
            sum.multiply(BigDecimal.valueOf((100 - discountCard.getBit())*0.01));
        }
        check.put("sum:",sum);
        return check;
    }
    public static Builder newBuilder() {
        return new Check().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setProducts(Map<Product, Integer> products){
            Check.this.products = products;
            return this;
        }
        public Builder setDiscountCard(DiscountCard card){
            Check.this.discountCard = card;
            return this;
        }

        public Check build() {
            return Check.this;
        }
    }
}
