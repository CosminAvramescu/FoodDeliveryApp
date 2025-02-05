package com.project.fooddeliveryapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import org.apache.log4j.Logger;

@Aspect
@Configuration
public class LoggingAspect {
    private static final Logger logger = Logger.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.project.fooddeliveryapp.service.MenuService.*(..))")
    public void menuServiceMethods() {
    }

    @Pointcut("execution(* com.project.fooddeliveryapp.service.OrderService.*(..))")
    public void orderServiceMethods() {
    }

    @Pointcut("execution(* com.project.fooddeliveryapp.service.UserService.*(..))")
    public void userServiceMethods() {
    }

    @Pointcut("execution(* com.project.fooddeliveryapp.service.ItemService.*(..))")
    public void itemServiceMethods() {
    }

    @Pointcut("execution(* com.project.fooddeliveryapp.service.RestaurantService.*(..))")
    public void restaurantServiceMethods() {
    }

    @Before("menuServiceMethods()")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        logger.info("[MENUS] - Executing method: " + joinPoint.getSignature());
    }

    @After("menuServiceMethods()")
    public void logAfterMethodExecution(JoinPoint joinPoint) {
        logger.info("[MENUS] - Method executed: " + joinPoint.getSignature());
    }

    @Around("orderServiceMethods()")
    public Object logAroundMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("[ORDERS] - Executing method: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        logger.info("[ORDERS] - Method executed: " + joinPoint.getSignature());
        return result;
    }

    @Around("userServiceMethods()")
    public Object logAroundMethodExecutionUser(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("[USERS] - Executing method: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        logger.info("[USERS] - Method executed: " + joinPoint.getSignature());
        return result;
    }

    @Around("itemServiceMethods()")
    public Object logAroundMethodExecutionItem(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("[ITEMS] - Executing method: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        logger.info("[ITEMS] - Method executed: " + joinPoint.getSignature());
        return result;
    }

    @Around("restaurantServiceMethods()")
    public Object logAroundMethodExecutionRestaurant(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("[RESTAURANTS] - Executing method: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        logger.info("[RESTAURANTS] - Method executed: " + joinPoint.getSignature());
        return result;
    }
}