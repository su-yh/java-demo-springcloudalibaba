package com.abc.controller;

import com.abc.bean.Depart;
import com.abc.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 消费者Controller
 * 以Feign的方式实现的服务降级的controller，主要是与之前的做区分
 */
@RestController
@RequestMapping("/feign/consumer/depart")
public class DepartFeignController {

    // suyh - FeignClient service
    @Autowired
    private DepartService departService;

    //跨服务新增
    @PostMapping("/save")
    public boolean saveHandle(@RequestBody Depart depart) {
        return departService.saveDepart(depart);
    }
    //跨服务根据id删除
    @DeleteMapping("/del/{id}")
    public void deleteHandle(@PathVariable("id") int id) {
        departService.delDepartById(id);
    }
    //跨服务修改
    @PutMapping("/update")
    public void updateHandle(@RequestBody Depart depart) {
        departService.modifyDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        Depart depart = departService.getDepartById(id);
        return depart;
    }
    //跨服务根据列表查询
    @GetMapping("/list")
    public List<Depart> listHandle() {
        List<Depart> departList = departService.listAllDeparts();
        return departList;
    }
}
