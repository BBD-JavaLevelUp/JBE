package com.jbe.server.controller;

import com.jbe.server.entity.Bean;
import com.jbe.server.entity.BuyOrder;
import com.jbe.server.service.BuyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buy_order")
public class BuyOrderController {
    private final BuyOrderService buyOrderService;

    @Autowired
    public BuyOrderController(BuyOrderService buyOrderService) {
        this.buyOrderService = buyOrderService;
    }

    @GetMapping
    public List<BuyOrder> getAllBuyOrders() {
        return buyOrderService.getAllBuyOrders();
    }

    @PostMapping
    public int saveBean(@RequestBody BuyOrder buyOrder){
        buyOrderService.saveOrUpdate(buyOrder);
        return buyOrder.getBeanId();
    }
}
