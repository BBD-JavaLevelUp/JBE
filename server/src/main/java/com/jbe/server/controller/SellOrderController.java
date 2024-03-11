package com.jbe.server.controller;

import com.jbe.server.entity.SellOrder;
import com.jbe.server.service.SellOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/sell-order")
public class SellOrderController {
    private final SellOrderService sellOrderService;

    @Autowired
    public SellOrderController(SellOrderService sellOrderService) {
        this.sellOrderService = sellOrderService;
    }

    @GetMapping
    public List<SellOrder> sellOrders()
    {
        return sellOrderService.getAllSellOrders();
    }

    @GetMapping("/investor/{id}")
    public List<SellOrder> sellOrdersByInvestor(@PathVariable int id)
    {
        return sellOrderService.getSellOrdersByInvestor(id);
    }

    @GetMapping("/beans/{id}")
    public List<SellOrder> sellOrdersByBean(@PathVariable int id)
    {
        return sellOrderService.getSellOrdersByBean(id);
    }

    @GetMapping("/active")
    public List<SellOrder> activeSellOrders()
    {
        return sellOrderService.getAllActiveSellOrders();
    }

    @GetMapping("/active/investor/{id}")
    public List<SellOrder> activeSellOrdersByInvestor(@PathVariable int id)
    {
        return sellOrderService.getAllActiveSellOrdersByInvestor(id);
    }

    @GetMapping("/active/beans/{id}")
    public List<SellOrder> activeSellOrdersByBean(@PathVariable int id)
    {
        return sellOrderService.getAllActiveSellOrdersByBean(id);
    }

    @GetMapping("/inactive")
    public List<SellOrder> inactiveSellOrders()
    {
        return sellOrderService.getAllInactiveSellOrders();
    }

    @GetMapping("/inactive/investor/{id}")
    public List<SellOrder> inactiveSellOrdersByInvestor(@PathVariable int id)
    {
        return sellOrderService.getAllInactiveSellOrdersByInvestor(id);
    }

    @GetMapping("/inactive/beans/{id}")
    public List<SellOrder> inactiveSellOrdersByBean(@PathVariable int id)
    {
        return sellOrderService.getAllInactiveSellOrdersByBean(id);
    }

    @GetMapping("/bean-prices/{id}")
    public List<BigDecimal> getPrices(@PathVariable int id)
    {
        List<SellOrder> sellOrders = sellOrderService.getAllActiveSellOrdersByBean(id);
        BigDecimal total = sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal jbePrice = total.divide(BigDecimal.valueOf(sellOrders.size()));
        BigDecimal marketPrice = sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.valueOf(99999999999999999L), BigDecimal::min);
        return List.of(jbePrice, marketPrice);
    }
}
