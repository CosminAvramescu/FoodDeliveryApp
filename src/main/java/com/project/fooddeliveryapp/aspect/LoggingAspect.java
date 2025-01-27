package com.project.fooddeliveryapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
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
        System.out.println("[MENUS] - Executing method: " + joinPoint.getSignature());
    }

    @After("menuServiceMethods()")
    public void logAfterMethodExecution(JoinPoint joinPoint) {
        System.out.println("[MENUS] - Method executed: " + joinPoint.getSignature());
    }

    @Around("orderServiceMethods()")
    public Object logAroundMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[ORDERS] - Executing method: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        System.out.println("[ORDERS] - Method executed: " + joinPoint.getSignature());
        return result;
    }

    @Around("userServiceMethods()")
    public Object logAroundMethodExecutionUser(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[USERS] - Executing method: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        System.out.println("[USERS] - Method executed: " + joinPoint.getSignature());
        return result;
    }

    @Around("itemServiceMethods()")
    public Object logAroundMethodExecutionItem(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[ITEMS] - Executing method: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        System.out.println("[ITEMS] - Method executed: " + joinPoint.getSignature());
        return result;
    }

    @Around("restaurantServiceMethods()")
    public Object logAroundMethodExecutionRestaurant(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[RESTAURANTS] - Executing method: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        System.out.println("[RESTAURANTS] - Method executed: " + joinPoint.getSignature());
        return result;
    }
}