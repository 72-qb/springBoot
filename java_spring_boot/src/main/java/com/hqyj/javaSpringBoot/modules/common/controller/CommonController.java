package com.hqyj.javaSpringBoot.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qb
 * @version 1.0
 * NO.1公共页面的跳转处理
 * come on
 * @date 2020/8/21 13:30
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    /*跳转到主页面*/
    @RequestMapping("/dashboard")
    public String dashborad(){
        return "index";
    }

    /*跳转到错误页面*/
    @GetMapping("/403")
    public String errorPageFor403(){
        return "index";
    }
}
