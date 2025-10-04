package com.MiniEcom.EcommerceBackend.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    //return type, class name, method name, args

    @Before("execution(* com.MiniEcom.ECommerceBackend.service.OrderService.*(..))\"")
    public void logMethodCall(JoinPoint joinPoint){
        LOGGER.info("Method Called");
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Method Called: {} with arguments {}",methodName, Arrays.toString(args));
    }

    @After("execution(* com.MiniEcom.ECommerceBackend.service.OrderService.*(..))")
    public void logAfterMethodCall(JoinPoint joinPoint){
        LOGGER.info("After Method Call");
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        LOGGER.info("After Method Called: {} with arguments {}",methodName, Arrays.toString(args));
    }

    @AfterThrowing("execution(* com.MiniEcom.ECommerceBackend.service.OrderService.*(..))")
    public void logAfterExceptionMethodCall(JoinPoint joinPoint){
        LOGGER.info("Method Failed");
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Method Threw Exception: {} with arguments {}",methodName, Arrays.toString(args));
    }

    @AfterReturning("execution(* com.MiniEcom.ECommerceBackend.service.OrderService.*(..))")
    public void logAfterSuccessfullMethodCall(JoinPoint joinPoint){
        LOGGER.info("Method Completed Successfully");
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        LOGGER.info("Method Executed Successfully: {} with arguments {}",methodName, Arrays.toString(args));
    }
}
