package com.hqyj.javaSpringBoot.modules.common.controller;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on异常的统一处理
 * @date 2020/8/21 13:30
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerController {

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result<String> handle403() {
        return new Result<>(Result.ResultStatus.FATLD.status,
                "", "/common/403");
    }
}
