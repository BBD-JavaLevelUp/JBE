package com.jbe.controller;


import com.jbe.entity.Bean;
import com.jbe.service.BeanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/beans")
public class BeanController {
    private final BeanService beanService;

    public BeanController(BeanService beanService) {
        this.beanService = beanService;
    }

    @GetMapping
    public List<Bean> getAllBeans() {
        return beanService.getAllBeans();
    }

}