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
 * @date 2020/8/17 19:25
 */
@Aspect
@Component
public class ControllerAspect {
    private final static Logger LOGGER= LoggerFactory.getLogger(ControllerAspect.class);

    /*
    * 关联在方法上的切点
    * 第一个*代表返回值的类型不限
    *第二个*代表module下所有的子包
    *第三个*代表所有的类
    * 第四个*代表所有的方法
    * (..)代表参数不限
    * Oeder(*)代表优先级，里面的数字越小优先级越高
    * */
    @Pointcut("execution(public * com.hqyj.javaSpringBoot.modules.*.controller.*.*(..))")
    public void controllerPointCut(){}

    @Before(value = "com.hqyj.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinPoint){
        LOGGER.debug("=======This is before controller=========");
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

    @Around(value = "com.hqyj.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public Object aroundController(ProceedingJoinPoint point) throws Throwable {
        LOGGER.debug("============This is around controller=========");
        return point.proceed(point.getArgs());
    }

    @After(value = "com.hqyj.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void afterController(){
        LOGGER.debug("======This is after controller==========");
    }

}
