package com.hqyj.javaSpringBoot.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/users")
    public String usersPage(){
        return "index";
    }
}
