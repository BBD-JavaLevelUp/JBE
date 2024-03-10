package com.jbe.client.Models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class SellOrder {
    private int sellOrderId;
    private int investorId;
    private int beanId;
    private BigDecimal sellingPrice;
    private long availableAmount;
    private long totalAmount;
    private OffsetDateTime sellOrderDate;
    private boolean isActive;

    // Constructor
    public SellOrder(int sellOrderId, int investorId, int beanId, BigDecimal sellingPrice, int availableAmount, int totalAmount, OffsetDateTime sellOrderDate, boolean isActive) {
        this.sellOrderId = sellOrderId;
        this.investorId = investorId;
        this.beanId = beanId;
        this.sellingPrice = sellingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.sellOrderDate = sellOrderDate;
        this.isActive = isActive;
    }

    public SellOrder(int investorId, int beanId, BigDecimal sellingPrice, int availableAmount, int totalAmount, OffsetDateTime sellOrderDate, boolean isActive) {
        this.investorId = investorId;
        this.beanId = beanId;
        this.sellingPrice = sellingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.sellOrderDate = sellOrderDate;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(int sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public int getInvestorId() {
        return investorId;
    }

    public void setInvestorId(int investorId) {
        this.investorId = investorId;
    }

    public int getBeanId() {
        return beanId;
    }

    public void setBeanId(int beanId) {
        this.beanId = beanId;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public long getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OffsetDateTime getSellOrderDate() {
        return sellOrderDate;
    }

    public void setSellOrderDate(OffsetDateTime sellOrderDate) {
        this.sellOrderDate = sellOrderDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
