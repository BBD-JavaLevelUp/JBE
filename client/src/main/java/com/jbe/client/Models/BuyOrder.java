package com.jbe.client.Models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class BuyOrder {
    private int buyOrderId;
    private int investorId;
    private int beanId;
    private BigDecimal buyingPrice;
    private long availableAmount;
    private long totalAmount;
    private OffsetDateTime buyOrderDate;
    private boolean isActive;

    // Constructor
    public BuyOrder(int buyOrderId, int investorId, int beanId, BigDecimal buyingPrice, int availableAmount, int totalAmount, OffsetDateTime buyOrderDate, boolean isActive) {
        this.buyOrderId = buyOrderId;
        this.investorId = investorId;
        this.beanId = beanId;
        this.buyingPrice = buyingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.buyOrderDate = buyOrderDate;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(int buyOrderId) {
        this.buyOrderId = buyOrderId;
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

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
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

    public OffsetDateTime getBuyOrderDate() {
        return buyOrderDate;
    }

    public void setBuyOrderDate(OffsetDateTime buyOrderDate) {
        this.buyOrderDate = buyOrderDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
