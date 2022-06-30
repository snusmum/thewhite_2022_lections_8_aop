package com.thewhite.students.aop.aspect;

import com.thewhite.students.aop.annotation.ValidateExecutionTime;
import com.thewhite.students.aop.annotation.LogExecutionTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Around("@annotation(logExecutionTime)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        long before = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long after = System.currentTimeMillis();

        long duration = after - before;
        String formattedTime = String.valueOf(
                switch (logExecutionTime.outputFormat()) {
                    case MILLIS -> duration;
                    case SECONDS -> TimeUnit.MILLISECONDS.toSeconds(duration);
                    case MINUTES -> TimeUnit.MILLISECONDS.toMinutes(duration);
                });

        System.out.printf("""
                          ---
                          Time spend: %s
                          ---
                          """,
                          formattedTime);

        return result;
    }

    @Before("@annotation(validateExecutionTime)")
    public void beforeAdvice(JoinPoint joinPoint, ValidateExecutionTime validateExecutionTime) {
        long duration = (long) joinPoint.getArgs()[0];
        if (duration < 0) {
            throw new RuntimeException("Negative duration (%s)".formatted(duration));
        }
    }

}
