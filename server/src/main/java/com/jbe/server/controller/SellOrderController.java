package com.jbe.server.controller;

import com.jbe.server.entity.SellOrder;
import com.jbe.server.service.SellOrderService;
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

    public SellOrderController(SellOrderService sellOrderService) {
        this.sellOrderService = sellOrderService;
    }

    @GetMapping
    public List<SellOrder> sellOrders()
    {
        return List.of(new SellOrder(0,0,0, BigDecimal.ZERO, 0L, 0L, true));
    }

    @GetMapping("/investor/{id}")
    public List<SellOrder> sellOrdersByInvestor(@PathVariable int id)
    {
        return List.of(new SellOrder(0,id,0, BigDecimal.ZERO, 0L, 0L, true));
    }

    @GetMapping("/beans/{id}")
    public List<SellOrder> sellOrdersByBean(@PathVariable int id)
    {
        return List.of(new SellOrder(0,0,id, BigDecimal.ZERO, 0L, 0L, true));
    }

    @GetMapping("/active")
    public List<SellOrder> activeSellOrders()
    {
        return List.of(new SellOrder(0,0,0, BigDecimal.ZERO, 0L, 0L, true));
    }

    @GetMapping("/sell-orders/active/investor/{id}")
    public List<SellOrder> activeSellOrdersByInvestor(@PathVariable int id)
    {
        return List.of(new SellOrder(0,id,0, BigDecimal.ZERO, 0L, 0L, true));
    }

    @GetMapping("/active/beans/{id}")
    public List<SellOrder> activeSellOrdersByBean(@PathVariable int id)
    {
        return List.of(new SellOrder(0,0,id, BigDecimal.ZERO, 0L, 0L, true));
    }

    @GetMapping("/inactive")
    public List<SellOrder> inactiveSellOrders()
    {
        return List.of(new SellOrder(0,0,0, BigDecimal.ZERO, 0L, 0L, false));
    }

    @GetMapping("/inactive/investor/{id}")
    public List<SellOrder> inactiveSellOrdersByInvestor(@PathVariable int id)
    {
        return List.of(new SellOrder(0,id,0, BigDecimal.ZERO, 0L, 0L, false));
    }

    @GetMapping("/inactive/beans/{id}")
    public List<SellOrder> inactiveSellOrdersByBean(@PathVariable int id)
    {
        return List.of(new SellOrder(0,0,id, BigDecimal.ZERO, 0L, 0L, false));
    }
}
