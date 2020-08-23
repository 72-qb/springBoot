package com.hqyj.javaSpringBoot.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/21 13:30
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/dashboard")
    public String dashborad(){
        return "index";
    }
}
