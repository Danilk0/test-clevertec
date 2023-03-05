package com.moskalyuk.clevertec.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Pointcuts includes pointcut
 */
@Component
@Aspect
public class Pointcuts {
    /**
     * Find repository layer
     */
    @Pointcut("@within(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer(){}

    /**
     * Find get method in repository layer
     */
    @Pointcut("isRepositoryLayer() execution(* *Repository.findById(*))")
    public void isGetMethod(){}

    /**
     * Find post method in repository layer
     */
    @Pointcut("isRepositoryLayer() execution(* *Repository.save(*))")
    public void isPostMethod(){}

    /**
     * Find delete method in repository layer
     */
    @Pointcut("isRepositoryLayer() execution(* *Repository.delete(*))")
    public void isDeleteMethod(){}

    /**
     * Find update method in repository layer
     */
    @Pointcut("isRepositoryLayer() execution(* *Repository.saveAndFlush(*))")
    public void isUpdateMethod(){}
}
