package com.jbe.server.controller;

import com.jbe.server.entity.SellOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/sell-orders")
public class SellOrderController {

    @GetMapping
    public List<SellOrder> sellOrders()
    {
        return List.of(new SellOrder(0,0,0, BigDecimal.ZERO, 0L, 0L, true));
    }
}
