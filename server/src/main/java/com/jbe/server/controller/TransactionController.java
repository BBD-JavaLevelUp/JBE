package com.jbe.server.controller;

import com.jbe.server.entity.Bean;
import com.jbe.server.entity.BuyOrder;
import com.jbe.server.entity.Investor;
import com.jbe.server.entity.Transaction;
import com.jbe.server.service.BeanService;
import com.jbe.server.service.BuyOrderService;
import com.jbe.server.service.InvestorService;
import com.jbe.server.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final BuyOrderService buyOrderService;
    private final BeanService beanService;

    @Autowired
    public TransactionController(TransactionService transactionService, BuyOrderService buyOrderService, BeanService beanService) {
        this.transactionService = transactionService;
        this.buyOrderService = buyOrderService;
        this.beanService = beanService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions().stream().map(t ->
        {
            Transaction n = new Transaction(t);
            BuyOrder buyOrder = buyOrderService.getBuyOrdersById(t.getBuyOrderId());
            Bean bean = beanService.getBeanById(buyOrder.getBeanId());
            n.setBeanName(bean.getName());
            return n;
        }).toList();
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactions(@PathVariable("transactionId") int transactionId) {
        Transaction n = transactionService.getTransactionById(transactionId);
        BuyOrder buyOrder = buyOrderService.getBuyOrdersById(n.getBuyOrderId());
        Bean bean = beanService.getBeanById(buyOrder.getBeanId());
        n.setBeanName(bean.getName());
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
