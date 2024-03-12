package com.jbe.server.service;

import com.jbe.server.entity.Investor;
import com.jbe.server.entity.Transaction;
import com.jbe.server.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(int transactionId) {
        return transactionRepository.findById(transactionId).get();
    }

    public List<Transaction> getTransactionByBean(int beanId) {
        return transactionRepository.findAllByBeanId(beanId);
    }

    public List<Transaction> getTransactionsByInvestor(int investorId) {
        return transactionRepository.findAllByInvestorId(investorId);
    }

    public void saveOrUpdate(Transaction transaction){
        transactionRepository.save(transaction);
    }
}
