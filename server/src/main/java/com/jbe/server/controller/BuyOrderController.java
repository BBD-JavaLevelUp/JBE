package com.jbe.server.controller;

import com.jbe.server.entity.Bean;
import com.jbe.server.entity.BuyOrder;
import com.jbe.server.service.BuyOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyOrders")
public class BuyOrderController {
    private final BuyOrderService buyOrderService;

    public BuyOrderController(BuyOrderService buyOrderService) {
        this.buyOrderService = buyOrderService;
    }

    @GetMapping("/buyOrders")
    public List<BuyOrder> getAllBuyOrders() {
        return buyOrderService.getAllBuyOrders();
    }

    @PostMapping("/buyOrder")
    public int saveBean(@RequestBody BuyOrder buyOrder){
        buyOrderService.saveOrUpdate(buyOrder);
        return buyOrder.getId();
    }
}
