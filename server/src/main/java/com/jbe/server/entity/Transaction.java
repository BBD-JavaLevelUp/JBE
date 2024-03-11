package com.jbe.server.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private int buyOrderId;
    private int sellOrderId;
    private OffsetDateTime transactionDate;
    private Long amount;

    public Transaction(int buyOrderId, int sellOrderId, Long amount){
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.amount = amount;
        this.transactionDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
    }

    public Transaction(int transactionId,int buyOrderId, int sellOrderId, Long amount){
        this.transactionId = transactionId;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.amount = amount;
        this.transactionDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
    }

    public Transaction(int transactionId,int buyOrderId, int sellOrderId, Long amount, OffsetDateTime transactionDate){
        this.transactionId = transactionId;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(int buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public int getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(int sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public OffsetDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(OffsetDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}