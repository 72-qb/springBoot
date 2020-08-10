package com.hqyj.javaSpringBoot.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/10 12:53
 */
@Controller
@RequestMapping("/test")
public class TestController {
    /**
     *http://localhost:8080/test/test01
     * get请求
     *
     * */
    @GetMapping("/test01")
    @ResponseBody
    public String test01(){
        return "hello spring-boot";
    }
}
