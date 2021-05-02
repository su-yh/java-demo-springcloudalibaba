package com.abc.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 目标：自定义filter，拦截请求对象，并且设置参数
 * 1.获取请求对象，并且自定义参数
 * 2.将请求对象设置到交换机exchange中
 * 3.放行请求
 * 4.在05-showinfo中，打印request的参数打印一下
 */
// TODO: suyh - 如果想要它生效，需要将它配置到路由中，可以查看BeanConfiguration 中的bean 的创建
// 这里实现的接口是GatewayFilter，那意思是它需要router 做为一个载体了？
// 所以我们才在配置路由里面添加该对象，使用new 的方式，而另外的一种是实现GlobalFilter
    // 它就是全局性的网关。对所有路由都生效。
public class AddHeaderGatewayFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求对象，并且自定义参数
        ServerHttpRequest request = exchange.getRequest().mutate().header("x-request-red", "blue").build();
        //2.将请求对象设置到交换机exchange中
        ServerWebExchange webExchange = exchange.mutate().request(request).build();
        //3.放行请求
        return chain.filter(webExchange);

    }
}
