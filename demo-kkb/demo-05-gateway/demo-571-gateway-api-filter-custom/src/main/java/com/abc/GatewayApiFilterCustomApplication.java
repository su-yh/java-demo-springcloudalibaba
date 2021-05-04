package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//启动引导类也是一个配置类
public class GatewayApiFilterCustomApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApiFilterCustomApplication.class, args);
    }

}
