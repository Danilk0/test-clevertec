package com.moskalyuk.clevertec.validation;

import com.moskalyuk.clevertec.validation.impl.UniqueProductNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueProductNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProductName {
    String message() default "This product name is duplicate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
