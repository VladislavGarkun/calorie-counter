package com.ibagroup.web.aop.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.ibagroup.web.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint){
        log.info("Logging before execution " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.ibagroup.web.controller.*.*(..))")
    public void logAfter(JoinPoint joinPoint){
        log.info("Logging after execution " + joinPoint.getSignature().getName());
    }

}
