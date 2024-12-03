package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAscpect {
    @Pointcut("within(com.example.demo.controller.*)")  //on met un pointcut sur le controller
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        //jointPOint permet de recuperer des informations des objects
        String methodname = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("methode :" + methodname + " args : " + Arrays.toString(args));
    }

    @AfterReturning(pointcut="controllerPointcut()", returning =  "response")
    public void logAfter(JoinPoint joinPoint, Object response) {

        String methodname = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("methode :" + methodname + " response :" + response);
    }

    @Around("controllerPointcut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        String methodname = joinPoint.getSignature().getName();
        System.out.println("Methode : " + methodname + "s'execute en " + (end -start) + "ms");
        return result;
    }

}
