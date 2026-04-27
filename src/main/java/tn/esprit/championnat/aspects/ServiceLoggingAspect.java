package tn.esprit.championnat.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    @Pointcut("execution(* tn.esprit.championnat.services..*(..))")
    public void serviceMethods() {
        // Pointcut centralise pour toutes les classes service.
    }

    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("[AOP][BEFORE] {}.{} | args={} ",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @Around("serviceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.info("[AOP][AROUND][START] {}.{}", className, methodName);
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            log.info("[AOP][AROUND][END] {}.{} terminee en {} ms", className, methodName, duration);
            return result;
        } catch (Throwable throwable) {
            long duration = System.currentTimeMillis() - start;
            log.error("[AOP][AROUND][ERROR] {}.{} en echec apres {} ms",
                    className, methodName, duration, throwable);
            throw throwable;
        }
    }

    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("[AOP][AFTER] {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AOP][AFTER_RETURNING] {}.{} | result={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("[AOP][AFTER_THROWING] {}.{} | message={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                exception.getMessage(),
                exception);
    }
}

