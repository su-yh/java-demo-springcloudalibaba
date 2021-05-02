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
}
