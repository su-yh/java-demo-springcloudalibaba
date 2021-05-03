package com.abc.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanConfiguration {
    public static void initFlowRule() {
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule qpsRule = BeanConfiguration.qpsFlowRule();
        flowRules.add(qpsRule);
        FlowRuleManager.loadRules(flowRules);
    }

    private static FlowRule qpsFlowRule() {
        //创建流控规则对象
        FlowRule qpsRule = new FlowRule();
        //设置流控资源名称
        qpsRule.setResource("qpsFlowRule");
        //设置流控规则【QPS和线程数】
        qpsRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置QPS数为1，限制为1，即：每秒允许一个请求通过
        qpsRule.setCount(1);
        //值为default，表示对请求来源不做限定
        qpsRule.setLimitApp("default");
        return qpsRule;
    }

    /**
     * 注入RestTemplate模板对象，用来发送http请求
     * 作用相当于：<bean id='' class='org.springframework.web.client.RestTemplate'></bean>
     *
     * RestTemplate底层有：
     *  HttpClient
     *  OKHttp
     *  ...
     *@LoadBalanced//如果想要使用注册中心的注册列表访问对应的服务，必须要开启本注解。
     *  1.采用服务名称访问：如果不加注解，即便你注册了服务，也不能使用名称
     *  2.开启负载均衡
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
