package com.moskalyuk.clevertec.validation.impl;

import com.moskalyuk.clevertec.database.repository.DiscountCardRepository;
import com.moskalyuk.clevertec.database.repository.ProductRepository;
import com.moskalyuk.clevertec.validation.UniqueProductName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName,String> {

    private final ProductRepository productRepository;

    @Autowired
    public UniqueProductNameValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return productRepository.findByName(value).isEmpty();
    }
}
