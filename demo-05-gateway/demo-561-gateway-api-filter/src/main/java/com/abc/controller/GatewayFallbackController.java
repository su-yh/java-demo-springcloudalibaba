package com.abc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayFallbackController {
    //定义服务降级处理方法
    @RequestMapping("/fallback")
    public String fallback(){
        return "This is the gateway fallback~";
    }
}
