package com.abc.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    //prefixPath过滤工厂 ： 添加前缀的
    @Bean
    public RouteLocator someRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(ps -> ps.path("/**")
                        .filters(fs -> fs.prefixPath("/consumer"))
                        .uri("http://localhost:8080")
                        .id("prefixPath_filter"))
                .build();
    }

    // @Bean
    public RouteLocator someRouteLocator02(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(ps -> ps.path("/**")
                        // 对该路由添加熔断降级的处理方法
                        .filters(fs -> fs.circuitBreaker(config -> {
                            config.setName("myCircuitBreaker");
                            config.setFallbackUri("forward:/fallback");
                        }))
                        .uri("http://localhost:8080")
                        .id("circuitBreaker_filter"))
                .build();
    }
}
