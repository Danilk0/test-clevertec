package com.moskalyuk.clevertec.validation;

import com.moskalyuk.clevertec.validation.impl.UniqueCardNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCardNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCardName {
    String message() default "This card name is duplicate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
