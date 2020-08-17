package com.hqyj.javaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/17 20:10
 */
@Aspect
@Component
public class ServiceAspect {

    private final static Logger LOGGER= LoggerFactory.getLogger(ServiceAspect .class);

    @Pointcut("@annotation(com.hqyj.javaSpringBoot.aspect.ServiceAnnotation)")
    @Order(1)
    public void servicePointCut(){}

    @Before(value = "com.hqyj.javaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public void beforeService(JoinPoint joinPoint){
        LOGGER.debug("=======This is before service=========");
        ServletRequestAttributes attributes=(ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        LOGGER.debug("请求来源：" + request.getRemoteAddr());
        LOGGER.debug("请求URL：" + request.getRequestURL().toString());
        LOGGER.debug("请求方式：" + request.getMethod());
        LOGGER.debug("响应方法：" +
                joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        LOGGER.debug("请求参数：" + Arrays.toString(joinPoint.getArgs()));
    }

    @Around(value = "com.hqyj.javaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public Object aroundService(ProceedingJoinPoint point) throws Throwable {
        LOGGER.debug("============This is around service=========");
        return point.proceed(point.getArgs());
    }

    @After(value = "com.hqyj.javaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public void afterService(){
        LOGGER.debug("======This is after service==========");
    }
}
