package com.xuehui;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class CouseDubboEdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouseDubboEdgeServiceApplication.class, args);
    }

}

