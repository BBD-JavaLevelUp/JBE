package com.jbe.server.controller;

import com.jbe.server.service.SellOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sellOrder")
public class SellOrderController {
    private final SellOrderService sellOrderService;

    public SellOrderController(SellOrderService sellOrderService) {
        this.sellOrderService = sellOrderService;
    }

}
