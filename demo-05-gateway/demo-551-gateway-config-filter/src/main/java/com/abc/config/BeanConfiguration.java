package com.abc.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class BeanConfiguration {
    //配置令牌桶限流算法的key：将hostName作为key返回回去
    // 该key 将在配置文件中使用
    @Bean
    public KeyResolver keyResolver(){
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getRemoteAddress()
                        .getHostName()
        );
    }
}
