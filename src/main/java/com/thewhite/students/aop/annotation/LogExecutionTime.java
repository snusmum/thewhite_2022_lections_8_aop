package com.thewhite.students.aop.annotation;

import com.thewhite.students.aop.aspect.TimeFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
    TimeFormat outputFormat() default TimeFormat.MILLIS;
}
