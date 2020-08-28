package com.hqyj.javaSpringBoot.modules.test.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/10 18:49
 */
@Component
@PropertySource("classpath:config/applicationTest.properties")
@ConfigurationProperties(prefix = "com.hq")
public class Application {
    private String port;

    private String name;

    private int age;

    private String desc;

    private String random;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
