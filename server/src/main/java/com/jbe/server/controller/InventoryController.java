package com.jbe.server.controller;

import com.jbe.server.entity.Bean;
import com.jbe.server.entity.BuyOrder;
import com.jbe.server.entity.Inventory;
import com.jbe.server.entity.SellOrder;
import com.jbe.server.service.BeanService;
import com.jbe.server.service.BuyOrderService;
import com.jbe.server.service.InventoryService;
import com.jbe.server.service.SellOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {
    private final InventoryService inventoryService;
    private final BeanService beanService;
    private final BuyOrderService buyOrderService;
    private final SellOrderService sellOrderService;

    @Autowired
    public InventoryController(InventoryService inventoryService, BeanService beanService, BuyOrderService buyOrderService, SellOrderService sellOrderService) {
        this.buyOrderService = buyOrderService;
        this.inventoryService = inventoryService;
        this.beanService = beanService;
        this.sellOrderService = sellOrderService;
    }

    @GetMapping
    public List<Inventory> inventories()
    {
        return inventoryService.getAllInventories().stream().map(i ->
        {
            Inventory n = new Inventory(i);
            Bean bean = beanService.getBeanById(n.getBeanId());
            List<BuyOrder> buyOrders = buyOrderService.getAllBuyOrdersByInvestorForBean(n.getInvestorId(),n.getBeanId());
            List<SellOrder> sellOrders = sellOrderService.getAllSellOrdersByInvestorForBean(n.getInvestorId(), n.getBeanId());
            if (!buyOrders.isEmpty()) {
                List<SellOrder> sellOrders1 = sellOrderService.getAllActiveSellOrdersByBean(n.getBeanId());
                BigDecimal marketPrice = bean.getDefaultPrice();
                if (!sellOrders1.isEmpty()) {
                    marketPrice = sellOrders1.stream().map(s -> s.getPrice()).reduce(BigDecimal.valueOf(9999999999L), BigDecimal::min).divide(BigDecimal.valueOf(sellOrders1.size()));
                }
                if (!sellOrders.isEmpty()) {
                    n.setProfit(marketPrice.multiply(BigDecimal.valueOf(n.getAmount())).subtract(buyOrders.stream().map(b -> b.getPrice().multiply(BigDecimal.valueOf(b.getTotalAmount() - b.getAvailableAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add)).add(sellOrders.stream().map(s -> s.getPrice().multiply(BigDecimal.valueOf(s.getTotalAmount() - s.getAvailableAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add)));
                } else {
                    n.setProfit(marketPrice.multiply(BigDecimal.valueOf(n.getAmount())).subtract(buyOrders.stream().map(b -> b.getPrice().multiply(BigDecimal.valueOf(b.getTotalAmount() - b.getAvailableAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add)));
                }
            } else {
                n.setProfit(BigDecimal.ZERO);
            }
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("investor/{id}")
    public List<Inventory> inventoriesForInvestorId (@PathVariable int id){
        return inventoryService.getInventoryForUser(id).stream().map(i ->
        {
            Inventory n = new Inventory(i);
            Bean bean = beanService.getBeanById(n.getBeanId());
            List<BuyOrder> buyOrders = buyOrderService.getAllBuyOrdersByInvestorForBean(n.getInvestorId(),n.getBeanId());
            List<SellOrder> sellOrders = sellOrderService.getAllSellOrdersByInvestorForBean(n.getInvestorId(), n.getBeanId());
            if (!buyOrders.isEmpty()) {
                List<SellOrder> sellOrders1 = sellOrderService.getAllActiveSellOrdersByBean(n.getBeanId());
                BigDecimal marketPrice = bean.getDefaultPrice();
                if (!sellOrders1.isEmpty()) {
                    marketPrice = sellOrders1.stream().map(s -> s.getPrice()).reduce(BigDecimal.valueOf(9999999999L), BigDecimal::min).divide(BigDecimal.valueOf(sellOrders1.size()));
                }
                n.setProfit(marketPrice.multiply(BigDecimal.valueOf(n.getAmount())).subtract(buyOrders.stream().map(b->b.getPrice().multiply(BigDecimal.valueOf(b.getTotalAmount()-b.getAvailableAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add)).add(sellOrders.stream().map(s->s.getPrice().multiply(BigDecimal.valueOf(s.getTotalAmount()-s.getAvailableAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add)));
            } else {
                n.setProfit(BigDecimal.ZERO);
            }
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }
}
