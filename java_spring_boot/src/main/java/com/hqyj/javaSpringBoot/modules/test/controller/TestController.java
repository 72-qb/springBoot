package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.test.vo.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Value("${server.port}")
    private String port;
    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.random}")
    private String random;

    @Autowired
    private Application application;

    /*
    * http://localhost:8085/test/logTest
    * */
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is a log24342423423";
    }

    /*
     * http://localhost:8085/test/config
     * */
    @GetMapping("/config")
    @ResponseBody
    public String config() {
        StringBuffer sb = new StringBuffer();
        sb.append(port).append("********")
                .append(name).append("********")
                .append(age).append("********")
                .append(desc).append("********")
                .append(random).append("********").append("<br>")
                .append(application.getPort()).append("********")
                .append(application.getName()).append("********")
                .append(application.getAge()).append("********")
                .append(application.getDesc()).append("********")
                .append(application.getRandom()).append("********");
        return sb.toString();
    }

    /**
     * http://localhost:8080/test/test01
     * get请求
     */
    @GetMapping("/test01")
    @ResponseBody
    public String test01() {
        return "hello spring-boot";
    }
}
