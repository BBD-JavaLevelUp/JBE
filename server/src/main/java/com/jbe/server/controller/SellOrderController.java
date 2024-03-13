package com.jbe.server.controller;

import com.jbe.server.entity.BuyOrder;
import com.jbe.server.entity.SellOrder;
import com.jbe.server.entity.Transaction;
import com.jbe.server.service.BuyOrderService;
import com.jbe.server.service.SellOrderService;
import com.jbe.server.service.TransactionService;
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

    @Autowired
    public SellOrderController(SellOrderService sellOrderService, BuyOrderService buyOrderService, TransactionService transactionService) {
        this.sellOrderService = sellOrderService;
        this.buyOrderService = buyOrderService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<SellOrder> sellOrders()
    {
        return sellOrderService.getAllSellOrders();
    }

    @GetMapping("/{id}")
    public SellOrder sellOrdersById(@PathVariable("id") int id)
    {
        return sellOrderService.getSellOrdersById(id);
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
        BigDecimal jbePrice = sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(sellOrders.size()));
        BigDecimal marketPrice = sellOrders.stream().map(s -> s.getPrice()).reduce(BigDecimal.valueOf(99999999999999999L), BigDecimal::min);
        return List.of(jbePrice, marketPrice);
    }

    @PostMapping
    public int saveSellOrder(@RequestBody SellOrder sellOrder){
        sellOrderService.saveOrUpdate(sellOrder);
        matchSellOrder(sellOrder);
        return sellOrder.getSellOrderId();
    }

    @PutMapping
    public int updateSellOrder(@RequestBody SellOrder sellOrder){
        sellOrderService.saveOrUpdate(sellOrder);
        matchSellOrder(sellOrder);
        return sellOrder.getSellOrderId();
    }

    public void matchSellOrder(SellOrder sellOrder){
        while (sellOrder.getAvailableAmount()>0){
            List<BuyOrder> buyOrders = new java.util.ArrayList<>(buyOrderService.getAllActiveBuyOrdersByBean(sellOrder.getBeanId()).stream().filter(b -> b.getPrice().compareTo(sellOrder.getPrice()) > 0).toList());
            buyOrders.sort(Comparator.comparing(BuyOrder::getOrderDate));
            if (!buyOrders.isEmpty()){
                BuyOrder buyOrder = buyOrders.getFirst();
                long total = min(List.of(sellOrder.getAvailableAmount(), buyOrder.getAvailableAmount()));
                Transaction transaction = new Transaction(buyOrder.getBuyOrderId(), sellOrder.getSellOrderId(), total);
                sellOrder.setAvailableAmount(sellOrder.getAvailableAmount()-total);
                buyOrder.setAvailableAmount(buyOrder.getAvailableAmount()-total);
                if (buyOrder.getAvailableAmount()==0){
                    List<Transaction> transactions = transactionService.getTransactionByBuyOrderId(buyOrder.getBuyOrderId());
                    buyOrder.setPrice(transactions.stream().map(t -> t.getPrice().multiply(BigDecimal.valueOf(t.getAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add));
                    buyOrder.setActive(false);
                }
                if (sellOrder.getAvailableAmount()==0){
                    sellOrder.setActive(false);
                }
                buyOrderService.saveOrUpdate(buyOrder);
                sellOrderService.saveOrUpdate(sellOrder);
                transactionService.saveOrUpdate(transaction);
            } else {
                return;
            }
        }
    }
}
