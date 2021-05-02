package com.suyh541.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class GatewayBeanConfig {
    //配置路由规则
    @Bean
    public RouteLocator someRouteLocator(RouteLocatorBuilder builder) {
        //路由构建器对象，构建一个路由规则
        return builder.routes()
                .route(predicateSpec -> predicateSpec
                        .path("/provider/depart/**")
                        .uri("lb://abcmsc-provider-depart")
                        .id("ribbon_route"))
                // 按理说这里可以多个route然后就可以接多个路由了
                .build();
    }
}
