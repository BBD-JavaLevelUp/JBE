package com.jbe.server.controller;

import com.jbe.server.entity.BuyOrder;
import com.jbe.server.entity.Investor;
import com.jbe.server.entity.Transaction;
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

    @Autowired
    public TransactionController(TransactionService transactionService, BuyOrderService buyOrderService) {
        this.transactionService = transactionService;
        this.buyOrderService = buyOrderService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactions(@PathVariable("transactionId") int transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @GetMapping("/beans/{beanId}")
    public List<Transaction> getTransactionsByBean(@PathVariable("beanId") int beanId) {
        return transactionService.getAllTransactions().stream()
                .filter(transaction -> {
                    BuyOrder currentBuyOrder = buyOrderService.getBuyOrdersById(transaction.getBuyOrderId());
                    return currentBuyOrder.getBeanId() == beanId;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/investor/{investorId}")
    public List<Transaction> getTransactionsByInvestor(@PathVariable("investorId") int investorId) {
        return transactionService.getAllTransactions().stream()
                .filter(transaction -> {
                    BuyOrder currentBuyOrder = buyOrderService.getBuyOrdersById(transaction.getBuyOrderId());
                    return currentBuyOrder.getInvestorId() == investorId;
                })
                .collect(Collectors.toList());
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
