package com.jbe.client.Models;

import java.util.Date;

public class SellOrder {
    private int sellOrderId;
    private int investorId;
    private int beanId;
    private double sellingPrice;
    private int availableAmount;
    private int totalAmount;
    private Date sellOrderDate;
    private boolean isActive;

    // Constructor
    public SellOrder(int sellOrderId, int investorId, int beanId, double sellingPrice, int availableAmount, int totalAmount, Date sellOrderDate, boolean isActive) {
        this.sellOrderId = sellOrderId;
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

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getSellOrderDate() {
        return sellOrderDate;
    }

    public void setSellOrderDate(Date sellOrderDate) {
        this.sellOrderDate = sellOrderDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
