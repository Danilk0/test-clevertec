package com.moskalyuk.clevertec.aop;

import com.moskalyuk.clevertec.caches.*;
import com.moskalyuk.clevertec.caches.annotation.DeleteCache;
import com.moskalyuk.clevertec.caches.annotation.GetCache;
import com.moskalyuk.clevertec.caches.annotation.PostCache;
import com.moskalyuk.clevertec.database.entity.BaseEntity;
import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CacheAspect add caching function to thr methods
 */
@Aspect
@Component
public class CacheAspect {

    /**
     * Caching algorithm from yaml file
     */
    @Value("${cache.algorithm}")
    public String algorithm;

    /**
     * Cache component
     */
    private  ICache<Integer,BaseEntity<Integer>> cache;

    /**
     * BaseFactory for dynamic initialization cache
     */
    private final BeanFactory beanFactory;

    /**
     * Inject BeanFactory
     * @param beanFactory
     */
    @Autowired
    public CacheAspect(BeanFactory beanFactory) {
        this.beanFactory=beanFactory;
    }

    /**
     *Inject cache algorithm
     */
    @PostConstruct
    public void setAlgorithm(){
        this.cache = beanFactory.getBean(algorithm.toUpperCase(), ICache.class);
    }

    /**
     * Advice to the get methods
     * @param joinPoint
     * @param id
     * @param getCache
     * @return
     * @throws Throwable
     */
    @Around("com.moskalyuk.clevertec.aop.Pointcuts.isGetMethod() && args(id) && @annotation(getCache)")
    public Object addCachingAroundGetMethods(ProceedingJoinPoint joinPoint, Object id, GetCache getCache) throws Throwable {

        Key cacheKey = new Key((Integer) id, getCache.value());

        Optional<? extends BaseEntity> entityFromCache = cache.get(cacheKey.hashCode());
        if(entityFromCache.isPresent()){
            return entityFromCache;
        }else{
            Object proceed = joinPoint.proceed();
            Optional<BaseEntity<Integer>> baseEntity = (Optional<BaseEntity<Integer>>) proceed;
            baseEntity.ifPresent(integerBaseEntity -> cache.set(cacheKey.hashCode(), integerBaseEntity));
            return proceed;
        }
    }

    /**
     * Advice to the post methods
     * @param joinPoint
     * @param postCache
     * @return
     * @throws Throwable
     */
    @Around("com.moskalyuk.clevertec.aop.Pointcuts.isPostMethod() && @annotation(postCache)")
    public Object addCachingAroundPostMethods(ProceedingJoinPoint joinPoint, PostCache postCache) throws Throwable {
        Object proceed = joinPoint.proceed();
        Key cacheKey = new Key(((BaseEntity<Integer>)proceed).getId(), postCache.value());
        BaseEntity<Integer> baseEntity = (BaseEntity<Integer>) proceed;
        cache.set(cacheKey.hashCode(), baseEntity);
        return proceed;
    }

    /**
     * Advice to the delete methods
     * @param joinPoint
     * @param entity
     * @param deleteCache
     * @throws Throwable
     */
    @Around("com.moskalyuk.clevertec.aop.Pointcuts.isDeleteMethod() && args(entity) && @annotation(deleteCache)")
    public void addCachingAroundDeleteMethods(ProceedingJoinPoint joinPoint, Object entity, DeleteCache deleteCache) throws Throwable {
        joinPoint.proceed();
        Key cacheKey = new Key(((BaseEntity<Integer>)entity).getId(), deleteCache.value());
        cache.delete(cacheKey.hashCode());
    }

    /**
     * Advice to the update methods
     * @param joinPoint
     * @param entity
     * @param updateCache
     * @return
     * @throws Throwable
     */
    @Around("com.moskalyuk.clevertec.aop.Pointcuts.isUpdateMethod() && args(entity) && @annotation(updateCache)")
    public Object addCachingAroundUpdateMethods(ProceedingJoinPoint joinPoint, Object entity, DeleteCache updateCache) throws Throwable {
        Object updateEntity = joinPoint.proceed();
        Key cacheKey = new Key(((BaseEntity<Integer>)entity).getId(), updateCache.value());
        cache.delete(cacheKey.hashCode());
        cache.set(cacheKey.hashCode(),(BaseEntity<Integer>) updateEntity);
        return updateEntity;
    }


}
