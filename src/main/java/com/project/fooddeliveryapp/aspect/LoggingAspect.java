package com.project.fooddeliveryapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

    @Before("execution(* com.project.fooddeliveryapp.service.*.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        System.out.println("Executing method: " + joinPoint.getSignature());
    }

    @After("execution(* com.project.fooddeliveryapp.service.*.*(..))")
    public void logAfterMethodExecution(JoinPoint joinPoint) {
        System.out.println("Method executed: " + joinPoint.getSignature());
    }
}
