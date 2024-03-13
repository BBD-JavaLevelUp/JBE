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
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.min;

@RestController
@RequestMapping("/api/buy-orders")
public class BuyOrderController {
    private final BuyOrderService buyOrderService;

    private final SellOrderService sellOrderService;

    private final TransactionService transactionService;

    @Autowired
    public BuyOrderController(BuyOrderService buyOrderService, SellOrderService sellOrderService, TransactionService transactionService) {
        this.buyOrderService = buyOrderService;
        this.sellOrderService = sellOrderService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<BuyOrder> getAllBuyOrders() {
        return buyOrderService.getAllBuyOrders();
    }

    @GetMapping("/{buyOrderId}")
    public BuyOrder buyOrdersById(@PathVariable("buyOrderId") int buyOrderId)
    {
        return buyOrderService.getBuyOrdersById(buyOrderId);
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
        matchBuyOrder(buyOrder);
        return buyOrder.getBeanId();
    }

    @PutMapping
    public int updateBean(@RequestBody BuyOrder buyOrder){
        buyOrderService.saveOrUpdate(buyOrder);
        matchBuyOrder(buyOrder);
        return buyOrder.getBeanId();
    }

    public void matchBuyOrder(BuyOrder buyOrder){
        while (buyOrder.getAvailableAmount()>0){
            List<SellOrder> sellOrders = new java.util.ArrayList<>(sellOrderService.getAllActiveSellOrdersByBean(buyOrder.getBeanId()).stream().filter(s -> s.getPrice().compareTo(buyOrder.getPrice()) < 0).toList());
            sellOrders.sort(Comparator.comparing(SellOrder::getOrderDate));
            if (!sellOrders.isEmpty()){
                SellOrder sellOrder = sellOrders.getFirst();
                long total = min(List.of(buyOrder.getAvailableAmount(), buyOrder.getAvailableAmount()));
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
