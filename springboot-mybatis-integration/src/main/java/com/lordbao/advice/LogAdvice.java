package com.lordbao.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author Lord_Bao
 * @Date 2024/8/18 17:20
 * @Version 1.0
 */
@Component
@Aspect
@Slf4j
public class LogAdvice {

    @Pointcut("execution(* com.lordbao.service..*.*(..))")
    public void logPointCut(){

    }

    @AfterReturning(value = "logPointCut()",returning = "returnValue")
    public void logAdvice(JoinPoint joinPoint,Object returnValue){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("方法 {}.{}: 返回结果: {}",className,methodName,returnValue);
    }
}
