package com.moskalyuk.clevertec.caches.annotation;

import com.moskalyuk.clevertec.database.entity.BaseEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateCache {

    Class<? extends BaseEntity> value();

}
