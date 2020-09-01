package com.hqyj.springcloud.springCloudClientTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTestApplication.class, args);
    }

}
