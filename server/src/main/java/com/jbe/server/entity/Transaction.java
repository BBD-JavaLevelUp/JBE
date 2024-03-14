package com.jbe.server.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private long amount;
    @Transient
    private String beanName;
    @Transient
    private BigDecimal price;

    public Transaction(){
    }

    public Transaction(int buyOrderId, int sellOrderId, long amount){
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.amount = amount;
        this.transactionDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
    }

    public Transaction(int transactionId,int buyOrderId, int sellOrderId, long amount){
        this.transactionId = transactionId;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.amount = amount;
        this.transactionDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
    }

    public Transaction(int transactionId,int buyOrderId, int sellOrderId, long amount, OffsetDateTime transactionDate){
        this.transactionId = transactionId;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Transaction(Transaction transaction) {
        this.transactionId = transaction.getTransactionId();
        this.buyOrderId = transaction.getBuyOrderId();
        this.sellOrderId = transaction.getSellOrderId();
        this.amount = transaction.getAmount();
        this.transactionDate = transaction.getTransactionDate();
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public OffsetDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(OffsetDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public BigDecimal getPrice() {
        if (price==null){
            return BigDecimal.ZERO;
        }
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}