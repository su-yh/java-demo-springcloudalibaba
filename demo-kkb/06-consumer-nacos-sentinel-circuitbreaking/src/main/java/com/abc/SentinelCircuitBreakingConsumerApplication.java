package com.abc;

import com.abc.config.BeanConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients//配置开启Feign的客户端支持
@SpringBootApplication
public class SentinelCircuitBreakingConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelCircuitBreakingConsumerApplication.class, args);
        //初始化熔断策略
        BeanConfiguration.initRule();
    }
}
