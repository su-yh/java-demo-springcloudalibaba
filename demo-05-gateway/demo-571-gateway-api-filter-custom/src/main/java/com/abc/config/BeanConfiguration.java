package com.abc.config;

import com.abc.filter.OneGatewayFilter;
import com.abc.filter.ThreeGatewayFilter;
import com.abc.filter.TwoGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    //配置自定义多过滤器
    @Bean
    public RouteLocator someRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(ps -> ps.path("/**")
                        // TODO: suyh - 想要该filter 生效，需要将它放到路中
                        .filters(fs -> fs.filter(new OneGatewayFilter())
                                .filter(new TwoGatewayFilter())
                                .filter(new ThreeGatewayFilter())) // 配置自定义网关
                        .uri("http://localhost:8080")
                        .id("custom_filter"))
                .build();
    }
}
