package com.thewhite.students.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class GetInfoAspect {

    @Pointcut("execution(* getInfo())")
    private void callGetInfo() {
    }

    @Around("callGetInfo()")
    public Object addPermissions(ProceedingJoinPoint joinPoint) throws Throwable {
        String datePrefix = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ": ";

        Object result = joinPoint.proceed();

        return datePrefix + (result == null ? "no info" : result);
    }
}
