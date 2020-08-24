package com.hqyj.javaSpringBoot.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/17 19:16
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping("/login")
    public String login(){
        return "indexSimple";
    }

    @RequestMapping("/register")
    public String register(){return "indexSimple";}

    @RequestMapping("/users")
    public String users(){
        return "index";
    }

    @RequestMapping("/roles")
    public String roles(){
        return "index";
    }

    @RequestMapping("/resources")
    public String resources(){
        return "index";
    }

    @RequestMapping("/profile")
    public String profile(){
        return "index";
    }

}
