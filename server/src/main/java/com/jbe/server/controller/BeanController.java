package com.jbe.server.controller;


import com.jbe.server.entity.Bean;
import com.jbe.server.entity.Inventory;
import com.jbe.server.entity.Investor;
import com.jbe.server.entity.SellOrder;
import com.jbe.server.service.BeanService;
import com.jbe.server.service.InventoryService;
import com.jbe.server.service.InvestorService;
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
    private final InventoryService inventoryService;

    @Autowired
    public BeanController(BeanService beanService, SellOrderService sellOrderService, InventoryService investorservice) {
        this.beanService = beanService;
        this.sellOrderService = sellOrderService;
        this.inventoryService = investorservice;
    }

    @GetMapping
    public List<Bean> getAllBeans() {
        return beanService.getAllBeans().stream().map(b ->
        {
            Bean n = new Bean(b);
            List<SellOrder> sellOrders = sellOrderService.getAllActiveSellOrdersByBean(n.getBeanId());
            if (!sellOrders.isEmpty()) {
                n.setJbePrice(sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(sellOrders.size())));
                n.setMarketPrice(sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.valueOf(99999999999999999L), BigDecimal::min));
            } else {
                n.setMarketPrice(n.getDefaultPrice());
                n.setJbePrice(n.getDefaultPrice());
            }
            return n;
        }).toList();
    }

    @GetMapping("/{beanId}")
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

    @PostMapping
    public int saveBean(@RequestBody List<Object> list){
        Bean bean = (Bean) list.get(0);
        long amount = (long) list.get(1);
        beanService.saveOrUpdate(bean);
        inventoryService.saveOrUpdate(new Inventory(1, bean.getBeanId(), amount));
        sellOrderService.saveOrUpdate(new SellOrder(1, bean.getBeanId(), bean.getDefaultPrice(), amount, amount, true));
        return bean.getBeanId();
    }

    @PutMapping
    public Bean updateBean(@RequestBody List<Object> list){
        Bean bean = (Bean) list.get(0);
        long amount = (long) list.get(1);
        beanService.saveOrUpdate(bean);
        Inventory inventory = inventoryService.getInventoryForUserByBean(1, bean.getBeanId());
        inventoryService.saveOrUpdate(new Inventory(1, bean.getBeanId(), inventory.getAmount()+amount));
        SellOrder sellOrder = sellOrderService.getAllSellOrdersByInvestorForBean(1, bean.getBeanId()).getFirst();
        sellOrderService.saveOrUpdate(new SellOrder(1, bean.getBeanId(), bean.getJbePrice(), sellOrder.getAvailableAmount()+amount, sellOrder.getTotalAmount()+amount, true));
        return bean;
    }

}