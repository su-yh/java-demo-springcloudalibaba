package com.abc.controller;

import com.abc.bean.Depart;
import org.springframework.beans.factory.annotation.Value;
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
public class DepartController {
    //使用@Value注解注入
    @Value("${depart.name:UNKNOWN-VALUE}")
    private String departName;

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
    public String getDepartName() {
        return departName;
    }
}
