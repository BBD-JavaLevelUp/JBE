package com.jbe.server.controller;

import com.jbe.server.entity.*;
import com.jbe.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final BuyOrderService buyOrderService;
    private final SellOrderService sellOrderService;
    private final BeanService beanService;

    @Autowired
    public TransactionController(TransactionService transactionService, BuyOrderService buyOrderService, BeanService beanService, SellOrderService sellOrderService) {
        this.transactionService = transactionService;
        this.buyOrderService = buyOrderService;
        this.beanService = beanService;
        this.sellOrderService = sellOrderService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions().stream().map(t ->
        {
            Transaction n = new Transaction(t);
            BuyOrder buyOrder = buyOrderService.getBuyOrdersById(t.getBuyOrderId());
            Bean bean = beanService.getBeanById(buyOrder.getBeanId());
            n.setBeanName(bean.getName());
            SellOrder sellOrder = sellOrderService.getSellOrdersById(t.getSellOrderId());
            n.setPrice(sellOrder.getPrice());
            return n;
        }).toList();
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactions(@PathVariable("transactionId") int transactionId) {
        Transaction n = transactionService.getTransactionById(transactionId);
        BuyOrder buyOrder = buyOrderService.getBuyOrdersById(n.getBuyOrderId());
        Bean bean = beanService.getBeanById(buyOrder.getBeanId());
        n.setBeanName(bean.getName());
        SellOrder sellOrder = sellOrderService.getSellOrdersById(n.getSellOrderId());
        n.setPrice(sellOrder.getPrice());
        return n;
    }

    @GetMapping("/beans/{beanId}")
    public List<Transaction> getTransactionsByBean(@PathVariable("beanId") int beanId) {
        return transactionService.getAllTransactions().stream()
                .filter(transaction -> {
                    BuyOrder currentBuyOrder = buyOrderService.getBuyOrdersById(transaction.getBuyOrderId());
                    return currentBuyOrder.getBeanId() == beanId;
                })
                .collect(Collectors.toList()).stream().map(t ->
                {
                    Transaction n = new Transaction(t);
                    BuyOrder buyOrder = buyOrderService.getBuyOrdersById(t.getBuyOrderId());
                    Bean bean = beanService.getBeanById(buyOrder.getBeanId());
                    n.setBeanName(bean.getName());
                    SellOrder sellOrder = sellOrderService.getSellOrdersById(t.getSellOrderId());
                    n.setPrice(sellOrder.getPrice());
                    return n;
                }).toList();
    }

    @GetMapping("/investor/{investorId}")
    public List<Transaction> getTransactionsByInvestor(@PathVariable("investorId") int investorId) {
        return transactionService.getAllTransactions().stream()
                .filter(transaction -> {
                    BuyOrder currentBuyOrder = buyOrderService.getBuyOrdersById(transaction.getBuyOrderId());
                    return currentBuyOrder.getInvestorId() == investorId;
                })
                .collect(Collectors.toList()).stream().map(t ->
                {
                    Transaction n = new Transaction(t);
                    BuyOrder buyOrder = buyOrderService.getBuyOrdersById(t.getBuyOrderId());
                    Bean bean = beanService.getBeanById(buyOrder.getBeanId());
                    n.setBeanName(bean.getName());
                    SellOrder sellOrder = sellOrderService.getSellOrdersById(t.getSellOrderId());
                    n.setPrice(sellOrder.getPrice());
                    return n;
                }).toList();
    }

    @GetMapping("/buy-orders/{id}")
    public List<Transaction> getTransactionsByBuyOrderId(@PathVariable int id) {
        return transactionService.getTransactionByBuyOrderId(id).stream().map(t ->
        {
            Transaction n = new Transaction(t);
            BuyOrder buyOrder = buyOrderService.getBuyOrdersById(t.getBuyOrderId());
            Bean bean = beanService.getBeanById(buyOrder.getBeanId());
            n.setBeanName(bean.getName());
            SellOrder sellOrder = sellOrderService.getSellOrdersById(t.getSellOrderId());
            n.setPrice(sellOrder.getPrice());
            return n;
        }).toList();
    }

    @GetMapping("/sell-orders/{id}")
    public List<Transaction> getTransactionsBySellOrderId(@PathVariable int id) {
        return transactionService.getTransactionByBuyOrderId(id).stream().map(t ->
        {
            Transaction n = new Transaction(t);
            BuyOrder buyOrder = buyOrderService.getBuyOrdersById(t.getBuyOrderId());
            Bean bean = beanService.getBeanById(buyOrder.getBeanId());
            n.setBeanName(bean.getName());
            SellOrder sellOrder = sellOrderService.getSellOrdersById(t.getSellOrderId());
            n.setPrice(sellOrder.getPrice());
            return n;
        }).toList();
    }

    @PostMapping
    public int saveTransaction(@RequestBody Transaction transaction){
        transactionService.saveOrUpdate(transaction);
        return transaction.getTransactionId();
    }

    @PutMapping
    public Transaction updateTransaction(@RequestBody Transaction transaction){
        transactionService.saveOrUpdate(transaction);
        return transaction;
    }
}
