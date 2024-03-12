package com.jbe.server.controller;


import com.jbe.server.entity.Bean;
import com.jbe.server.entity.SellOrder;
import com.jbe.server.service.BeanService;
import com.jbe.server.service.SellOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beans")
public class BeanController {
    private final BeanService beanService;
    private final SellOrderService sellOrderService;

    @Autowired
    public BeanController(BeanService beanService, SellOrderService sellOrderService) {
        this.beanService = beanService;
        this.sellOrderService = sellOrderService;
    }

    @GetMapping("/beans")
    public List<Bean> getAllBeans() {
        return beanService.getAllBeans().stream().map(b ->
        {
            Bean n = new Bean(b);
            List<SellOrder> sellOrders = sellOrderService.getAllActiveSellOrdersByBean(n.getBeanId());
            if (sellOrders.size()>0) {
                n.setJbePrice(sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(sellOrders.size())));
                n.setMarketPrice(sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.valueOf(99999999999999999L), BigDecimal::min));
            } else {
                n.setMarketPrice(null);
                n.setJbePrice(n.getDefaultPrice());
            }
            return n;
        }).toList();
    }

    @GetMapping("/beans/{beanId}")
    public Bean getBeans(@PathVariable("beanId") int beanId) {
        Bean bean = beanService.getBeanById(beanId);
        List<SellOrder> sellOrders = sellOrderService.getAllActiveSellOrdersByBean(beanId);
        if (!sellOrders.isEmpty()) {
            bean.setJbePrice(sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(sellOrders.size())));
            bean.setMarketPrice(sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.valueOf(99999999999999999L), BigDecimal::min));
        } else {
            bean.setMarketPrice(null);
            bean.setJbePrice(bean.getDefaultPrice());
        }
        return bean;
    }

    @PostMapping("/beans")
    public int saveBean(@RequestBody Bean bean){
        beanService.saveOrUpdate(bean);
        return bean.getBeanId();
    }

    @PutMapping("/beans")
    public Bean updateBean(@RequestBody Bean bean){
        beanService.saveOrUpdate(bean);
        return bean;
    }

}