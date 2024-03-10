package com.jbe.server.controller;


import com.jbe.server.entity.Bean;
import com.jbe.server.service.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beans")
public class BeanController {
    private final BeanService beanService;

    @Autowired
    public BeanController(BeanService beanService) {
        this.beanService = beanService;
    }

    @GetMapping("/beans")
    public List<Bean> getAllBeans() {
        return beanService.getAllBeans();
    }

    @GetMapping("/beans/{beanId}")
    public Bean getBeans(@PathVariable("beanId") int beanId) {
        return beanService.getBeanById(beanId);
    }

    @PostMapping("/bean")
    public int saveBean(@RequestBody Bean bean){
        beanService.saveOrUpdate(bean);
        return bean.getId();
    }

}