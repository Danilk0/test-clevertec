package com.moskalyuk.clevertec.validation.impl;

import com.moskalyuk.clevertec.database.repository.DiscountCardRepository;
import com.moskalyuk.clevertec.validation.UniqueProductName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName,String> {

    private final DiscountCardRepository discountCardRepository;

    @Autowired
    public UniqueProductNameValidator(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return discountCardRepository.findByName(value).isPresent();
    }
}
