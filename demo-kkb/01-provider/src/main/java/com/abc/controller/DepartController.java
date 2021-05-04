package com.abc.controller;

import com.abc.bean.Depart;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//提供者Controller，对外提供接口
@RequestMapping("/provider/depart")
@RestController
public class DepartController {

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
}
