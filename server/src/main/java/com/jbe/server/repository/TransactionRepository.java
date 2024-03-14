package com.jbe.server.repository;

import com.jbe.server.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public List<Transaction> findByBuyOrderId(int id);

    public List<Transaction> findBySellOrderId(int id);

}