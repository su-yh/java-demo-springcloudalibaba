package com.abc.controller;

import com.abc.bean.Depart;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 消费者Controller
 */
@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    //注入RestTemplate模板对象
    @Autowired
    private RestTemplate restTemplate;

    //发送http请求的，http://localhost:8081地址写死，去访问提供者行不行呢？
    //行、不行【】
    //原因1：如果我服务提供者集群部署：无法访问集群
    //原因2：如果服务发布上线，或者后期服务迁移，需要经常性修改配置文件【1万个服务呢】
    //原因：....负载均衡、熔断

    //配置请求地址
    //解决url地址写死的问题：
    // private static final String SERVICE_PROVIDER = "http://localhost:8081";
    //通过注册中心的地址去访问对应的服务：注册中心的机制：注册列表。http://{服务名称}
    private static final String SERVICE_PROVIDER = "http://msc-provider-depart";


    //跨服务新增
    @PostMapping("/save")
    public boolean saveHandle(@RequestBody Depart depart) {
        String url = SERVICE_PROVIDER + "/provider/depart/save";
        //问题：消费者直连提供者行不行？
        Boolean result = restTemplate.postForObject(url, depart, Boolean.class);
        return result;
    }
    //跨服务根据id删除
    @DeleteMapping("/del/{id}")
    public void deleteHandle(@PathVariable("id") int id) {
        String url = SERVICE_PROVIDER + "/provider/depart/del/" + id;
        restTemplate.delete(url);
    }
    //跨服务修改
    @PutMapping("/update")
    public void updateHandle(@RequestBody Depart depart) {
        String url = SERVICE_PROVIDER + "/provider/depart/update";
        restTemplate.put(url, depart, Boolean.class);
    }

    /*
     * @SentinelResource 注解作用：声明当前方法为Sentinel的一个资源
     * fallback属性：设置降级处理方法
     * value属性：设置资源的名称，该名称会在sentinel 中进行显示。
     */
    // suyh - 方法级别的降级
    //@SentinelResource(value = "getDepartById",fallback = "getHandleFallback")
    // suyh - 将降级方法抽取到一个静态类中
    @SentinelResource(fallback = "getFallback",
            fallbackClass = DepartServiceClassFallBack.class)
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        String url = SERVICE_PROVIDER + "/provider/depart/get/" + id;
        return restTemplate.getForObject(url, Depart.class);
    }
    //服务降级处理方法: 类的单一职责原则
    public Depart getHandleFallback(@PathVariable("id") int id) {
        Depart depart = new Depart();
        depart.setName("no any more");
        return depart;
    }
    //跨服务根据列表查询
    @GetMapping("/list")
    @SentinelResource(fallback = "listFallback"
            ,fallbackClass = DepartServiceClassFallBack.class)
    public List<Depart> listHandle() {
        String url = SERVICE_PROVIDER + "/provider/depart/list/";
        return restTemplate.getForObject(url, List.class);
    }
}
