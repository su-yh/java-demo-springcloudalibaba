package com.suyh711.config;

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
                        predicateSpec.path("/provider/depart/**")
                                .uri("lb://msc-suyh-provider")
                                .id("provider_router"))
                .route(predicateSpec ->
                        predicateSpec.path("/consumer/depart/**")
                                .uri("lb://msc-suyh-consumer")
                                .id("consumer_router"))
                .build();
    }
}
