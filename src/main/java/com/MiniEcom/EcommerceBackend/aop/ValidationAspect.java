package com.MiniEcom.EcommerceBackend.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.MiniEcom.ECommerceBackend.service.ProductService.getProductById(..)) && args(id)")
    public Object validateAndUpdate(ProceedingJoinPoint jp,int id) throws Throwable {

        if(id<=0){
            LOGGER.info("PostId is negative, updating it");
            id= -id;
            LOGGER.info("Post Id is: "+id);
        }

        Object obj = jp.proceed(new Object[]{id});

        return obj;
    }

}
