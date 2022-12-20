package com.moskalyuk.clevertec.dto;

import com.moskalyuk.clevertec.database.entity.DiscountCard;
import com.moskalyuk.clevertec.database.entity.Product;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Check {
    private Check(){}
    private Map<Product, Integer> products;
    private Optional<DiscountCard> discountCard;
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
        discountCard.ifPresent(card -> sum.multiply(BigDecimal.valueOf((100 - card.getBit()) * 0.01)));
        check.put("sum:",sum);
        return check;
    }

    @Override
    public String toString() {
        String products="";
        for (Map.Entry<String, BigDecimal> entry: getCheck().entrySet()){
            products+=entry.getKey()+" : "+entry.getValue().toString()+"$\n";
        }
        return "CHECK\n\n" +
                products +
                "discount card : " + discountCard.map(DiscountCard::getBit).orElse(0) + "%" + "\n" +
                "sum : " + sum+"$";
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
        public Builder setDiscountCard(Optional<DiscountCard> card){
            Check.this.discountCard = card;
            return this;
        }

        public Check build() {
            return Check.this;
        }
    }
}
