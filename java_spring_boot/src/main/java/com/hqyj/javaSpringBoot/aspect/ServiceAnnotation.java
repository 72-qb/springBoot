package com.hqyj.javaSpringBoot.aspect;

import java.lang.annotation.*;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/17 20:11
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceAnnotation {
    String value() default "abc";
}
