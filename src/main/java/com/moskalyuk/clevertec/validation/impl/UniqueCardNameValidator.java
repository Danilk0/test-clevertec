package com.moskalyuk.clevertec.validation.impl;

import com.moskalyuk.clevertec.database.repository.DiscountCardRepository;
import com.moskalyuk.clevertec.validation.UniqueCardName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueCardNameValidator implements ConstraintValidator<UniqueCardName,String> {

    private final DiscountCardRepository discountCardRepository;

    @Autowired
    public UniqueCardNameValidator(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return discountCardRepository.findByName(value).isPresent();
    }
}
