package com.suyh601.controller;

import com.suyh601.bean.Depart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//提供者Controller，对外提供接口
@RequestMapping("/provider/depart")
@RestController
@RefreshScope
public class DepartController {

    @Value("${depart.name:UNKNOWN-VALUE}")
    private String departName;

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${server.port}")
    private int port;

    //新增
    @PostMapping("/save")
    public boolean saveHandle(@RequestBody Depart depart) {
        return true;
    }

    //删除
    @DeleteMapping("/del/{id}")
    public boolean deleteHandle(@PathVariable("id") int id) {
        return true;
    }

    //修改
    @PutMapping("/update")
    public boolean updateHandle(@RequestBody Depart depart) {
        return true;
    }

    //根据id查询
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        return null;
    }

    //查询列表
    @GetMapping("/list")
    public List<Depart> listHandle() {
        return null;
    }

    @GetMapping("/depart/name")
    public String departName() {
        return departName + " - " + port;
    }

    @GetMapping("/service/info")
    public String serviceInfo() {
        return serviceName + ": " + port;
    }

    @GetMapping("/service/show")
    public String serviceShow() {
        return serviceName + ": " + port + " - " + departName;
    }
}
