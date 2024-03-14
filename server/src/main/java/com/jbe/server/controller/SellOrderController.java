package com.jbe.server.controller;

import com.jbe.server.entity.*;
import com.jbe.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.*;

@RestController
@RequestMapping("/api/sell-orders")
public class SellOrderController {
    private final SellOrderService sellOrderService;
    private final BuyOrderService buyOrderService;
    private final TransactionService transactionService;
    private final BeanService beanService;
    private final InventoryService inventoryService;

    @Autowired
    public SellOrderController(SellOrderService sellOrderService, BuyOrderService buyOrderService, TransactionService transactionService, BeanService beanService, InventoryService inventoryService) {
        this.sellOrderService = sellOrderService;
        this.buyOrderService = buyOrderService;
        this.transactionService = transactionService;
        this.beanService = beanService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<SellOrder> sellOrders()
    {
        return sellOrderService.getAllSellOrders().stream().map(s -> {
            SellOrder n = new SellOrder(s);
            Bean bean = beanService.getBeanById(s.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/{id}")
    public SellOrder sellOrdersById(@PathVariable("id") int id)
    {
        SellOrder n = new SellOrder(sellOrderService.getSellOrdersById(id));
        Bean bean = beanService.getBeanById(n.getBeanId());
        n.setBeanName(bean.getName());
        return n;
    }

    @GetMapping("/investor/{id}")
    public List<SellOrder> sellOrdersByInvestor(@PathVariable int id)
    {
        return sellOrderService.getSellOrdersByInvestor(id).stream().map(s -> {
            SellOrder n = new SellOrder(s);
            Bean bean = beanService.getBeanById(s.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/beans/{id}")
    public List<SellOrder> sellOrdersByBean(@PathVariable int id)
    {
        return sellOrderService.getSellOrdersByBean(id).stream().map(s -> {
            SellOrder n = new SellOrder(s);
            Bean bean = beanService.getBeanById(s.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/active")
    public List<SellOrder> activeSellOrders()
    {
        return sellOrderService.getAllActiveSellOrders().stream().map(s -> {
            SellOrder n = new SellOrder(s);
            Bean bean = beanService.getBeanById(s.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/active/investor/{id}")
    public List<SellOrder> activeSellOrdersByInvestor(@PathVariable int id)
    {
        return sellOrderService.getAllActiveSellOrdersByInvestor(id).stream().map(s -> {
            SellOrder n = new SellOrder(s);
            Bean bean = beanService.getBeanById(s.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/active/beans/{id}")
    public List<SellOrder> activeSellOrdersByBean(@PathVariable int id)
    {
        return sellOrderService.getAllActiveSellOrdersByBean(id).stream().map(s -> {
            SellOrder n = new SellOrder(s);
            Bean bean = beanService.getBeanById(s.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/inactive")
    public List<SellOrder> inactiveSellOrders()
    {
        return sellOrderService.getAllInactiveSellOrders().stream().map(s -> {
            SellOrder n = new SellOrder(s);
            Bean bean = beanService.getBeanById(s.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/inactive/investor/{id}")
    public List<SellOrder> inactiveSellOrdersByInvestor(@PathVariable int id)
    {
        return sellOrderService.getAllInactiveSellOrdersByInvestor(id).stream().map(s -> {
            SellOrder n = new SellOrder(s);
            Bean bean = beanService.getBeanById(s.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/inactive/beans/{id}")
    public List<SellOrder> inactiveSellOrdersByBean(@PathVariable int id)
    {
        return sellOrderService.getAllInactiveSellOrdersByBean(id).stream().map(s -> {
            SellOrder n = new SellOrder(s);
            Bean bean = beanService.getBeanById(s.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/bean-prices/{id}")
    public List<BigDecimal> getPrices(@PathVariable int id)
    {
        List<SellOrder> sellOrders = sellOrderService.getAllActiveSellOrdersByBean(id);
        BigDecimal jbePrice = sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(sellOrders.size()));
        BigDecimal marketPrice = sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.valueOf(99999999999999999L), BigDecimal::min);
        return List.of(jbePrice, marketPrice);
    }

    @PostMapping
    public int saveSellOrder(@RequestBody SellOrder sellOrder){
        Inventory inventory = inventoryService.getInventoryForUserByBean(sellOrder.getInvestorId(), sellOrder.getBeanId());
        if (sellOrder.getTotalAmount()<=inventory.getAmount()) {
            sellOrderService.saveOrUpdate(sellOrder);
            matchSellOrder(sellOrder);
            return sellOrder.getSellOrderId();
        } else {
            return 0;
        }
    }

    @PutMapping
    public int updateSellOrder(@RequestBody SellOrder sellOrder){
        sellOrderService.saveOrUpdate(sellOrder);
        matchSellOrder(sellOrder);
        return sellOrder.getSellOrderId();
    }

    public void matchSellOrder(SellOrder sellOrder){
        while (sellOrder.getAvailableAmount()>0){
            sellOrder.setActive(true);
            List<BuyOrder> buyOrders = new java.util.ArrayList<>(buyOrderService.getAllActiveBuyOrdersByBean(sellOrder.getBeanId()).stream().filter(b -> b.getPrice().compareTo(sellOrder.getPrice()) >= 0).toList());
            buyOrders.sort(Comparator.comparing(BuyOrder::getOrderDate));
            if (!buyOrders.isEmpty()){
                BuyOrder buyOrder = buyOrders.getFirst();
                Inventory sellerInventory = inventoryService.getInventoryForUserByBean(sellOrder.getInvestorId(), sellOrder.getBeanId());
                Inventory buyerInventory = inventoryService.getInventoryForUserByBean(buyOrder.getInvestorId(), buyOrder.getBeanId());
                if (buyerInventory==null){
                    buyerInventory = new Inventory(buyOrder.getInvestorId(), buyOrder.getBeanId(), 0L);
                }
                long total = min(List.of(sellOrder.getAvailableAmount(), buyOrder.getAvailableAmount()));
                Transaction transaction = new Transaction(buyOrder.getBuyOrderId(), sellOrder.getSellOrderId(), total);
                transaction.setPrice(sellOrder.getPrice());
                sellOrder.setAvailableAmount(sellOrder.getAvailableAmount()-total);
                buyOrder.setAvailableAmount(buyOrder.getAvailableAmount()-total);
                sellerInventory.setAmount(sellerInventory.getAmount()-total);
                buyerInventory.setAmount(buyerInventory.getAmount()+total);
                if (buyOrder.getAvailableAmount()==0){
                    List<Transaction> transactions = transactionService.getTransactionByBuyOrderId(buyOrder.getBuyOrderId());
                    transactions.add(transaction);
                    buyOrder.setPrice(transactions.stream().map(t -> t.getPrice().multiply(BigDecimal.valueOf(t.getAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(buyOrder.getTotalAmount())));
                    buyOrder.setActive(false);
                }
                if (sellOrder.getAvailableAmount()==0){
                    sellOrder.setActive(false);
                }
                buyOrderService.saveOrUpdate(buyOrder);
                sellOrderService.saveOrUpdate(sellOrder);
                transactionService.saveOrUpdate(transaction);
                inventoryService.saveOrUpdate(sellerInventory);
                inventoryService.saveOrUpdate(buyerInventory);
            } else {
                sellOrderService.saveOrUpdate(sellOrder);
                return;
            }
        }
    }
}
