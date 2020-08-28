package com.hqyj.javaSpringBoot.modules.account.controller;

import com.hqyj.javaSpringBoot.modules.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "indexSimple";
    }

    @RequestMapping("/register")
    public String register(){return "indexSimple";}

    @RequestMapping("/registerVue")
    public String registerVue(){return "indexSimple";}

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

    @GetMapping("/logout")
    public String logout(ModelMap map){
        userService.logout();
        map.addAttribute("template","account/login");
        return "indexSimple";
    }

}
