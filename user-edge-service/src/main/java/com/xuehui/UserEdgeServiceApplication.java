package com.xuehui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class UserEdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserEdgeServiceApplication.class, args);
    }

}