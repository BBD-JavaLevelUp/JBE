package com.jbe.server.controller;

import com.jbe.server.entity.Transaction;
import com.jbe.server.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/{transactionId}")
    public Transaction getTransactions(@PathVariable("transactionId") int transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @GetMapping("/transactions/beans/{beanId}")
    public List<Transaction> getTransactionsByBean(@PathVariable("beanId") int beanId) {
        return transactionService.getTransactionByBean(beanId);
    }

    @GetMapping("/transactions/investor/{investorId}")
    public List<Transaction> getTransactionsByInvestor(@PathVariable("investorId") int investorId) {
        return transactionService.getTransactionsByInvestor(investorId);
    }

    @PostMapping("/transactions")
    public int saveTransaction(@RequestBody Transaction transaction){
        transactionService.saveOrUpdate(transaction);
        return transaction.getTransactionId();
    }

    @PutMapping("/transactions")
    public Transaction updateTransaction(@RequestBody Transaction transaction){
        transactionService.saveOrUpdate(transaction);
        return transaction;
    }
}
