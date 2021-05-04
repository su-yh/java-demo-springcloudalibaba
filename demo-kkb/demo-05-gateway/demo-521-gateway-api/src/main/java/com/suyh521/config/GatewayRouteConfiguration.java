package com.suyh521.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class GatewayRouteConfiguration {

    //配置路由规则
    @Bean
    public RouteLocator someRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec ->
                        predicateSpec.path("/")
                                .uri("https://www.baidu.com")
                                .id("baidu_route"))
                // TODO: suyh - 这后面的163 没有生效呀。
                .route(predicateSpec ->
                        predicateSpec.path("/**/163")
                                .uri("https://www.163.com/")
                                .id("163_route"))
                .build();//函数式编程思想
    }
}
