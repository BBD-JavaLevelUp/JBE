package com.jbe.server.controller;

import com.jbe.server.entity.BuyOrder;
import com.jbe.server.service.BuyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buy-order")
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

    @GetMapping("/investor/{id}")
    public List<BuyOrder> buyOrdersByInvestor(@PathVariable int id)
    {
        return buyOrderService.getBuyOrdersByInvestor(id);
    }

    @GetMapping("/beans/{id}")
    public List<BuyOrder> buyOrdersByBean(@PathVariable int id)
    {
        return buyOrderService.getBuyOrdersByBean(id);
    }

    @GetMapping("/active")
    public List<BuyOrder> activeBuyOrders()
    {
        return buyOrderService.getAllActiveBuyOrders();
    }

    @GetMapping("/active/investor/{id}")
    public List<BuyOrder> activeBuyOrdersByInvestor(@PathVariable int id)
    {
        return buyOrderService.getAllActiveBuyOrdersByInvestor(id);
    }

    @GetMapping("/active/beans/{id}")
    public List<BuyOrder> activeBuyOrdersByBean(@PathVariable int id)
    {
        return buyOrderService.getAllActiveBuyOrdersByBean(id);
    }

    @GetMapping("/inactive")
    public List<BuyOrder> inactiveBuyOrders()
    {
        return buyOrderService.getAllInactiveBuyOrders();
    }

    @GetMapping("/inactive/investor/{id}")
    public List<BuyOrder> inactiveBuyOrdersByInvestor(@PathVariable int id)
    {
        return buyOrderService.getAllInactiveBuyOrdersByInvestor(id);
    }

    @GetMapping("/inactive/beans/{id}")
    public List<BuyOrder> inactiveBuyOrdersByBean(@PathVariable int id)
    {
        return buyOrderService.getAllInactiveBuyOrdersByBean(id);
    }

    @PostMapping
    public int saveBean(@RequestBody BuyOrder buyOrder){
        buyOrderService.saveOrUpdate(buyOrder);
        return buyOrder.getBeanId();
    }
}
