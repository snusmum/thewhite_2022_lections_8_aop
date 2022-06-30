package com.thewhite.students.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(com.thewhite.students.aop.service.*)")
    private void anyService() {
    }

    @Before("anyService()")
    public void showArgument(JoinPoint joinPoint) {
        String enterTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        joinPoint.getArgs();
        System.out.println(enterTime + ": call " + joinPoint.getSignature() + " with arguments " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("anyService()")
    public void printStackTrace() {
        new Throwable().printStackTrace();
    }

}
