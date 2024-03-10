package com.jbe.client.Models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransactionItem {
    private OffsetDateTime transactionDate;
    private String beanName;
    private long amount;
    private BigDecimal price;
    private String buyerName;
    private String sellerName;

    public TransactionItem(OffsetDateTime transactionDate, String beanName, long amount, BigDecimal price,String buyerName,String sellerName) {
        this.transactionDate = transactionDate;
        this.beanName = beanName;
        this.amount = amount;
        this.price = price;
        this.buyerName = buyerName;
        this.sellerName = sellerName;
        
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
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public String getSellerName() {
        return sellerName;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

}
