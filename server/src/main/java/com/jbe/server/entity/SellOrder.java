package com.jbe.server.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity
@Table
public class SellOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sellOrderId;
    private int sellingInvestorId;
    private int beanId;

    private BigDecimal sellingPrice;

    private long availableAmount;

    private long totalAmount;
    private OffsetDateTime orderDate;
    private boolean isActive;

    public SellOrder(int sellingInvestorId, int beanId, BigDecimal sellingPrice, Long availableAmount, Long totalAmount, boolean isActive){
        this.sellingInvestorId = sellingInvestorId;
        this.beanId = beanId;
        this.sellingPrice = sellingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
        this.isActive = isActive;
    }

    public SellOrder(int sellOrderId, int sellingInvestorId, int beanId, BigDecimal sellingPrice, Long availableAmount, Long totalAmount, boolean isActive){
        this.sellingInvestorId = sellingInvestorId;
        this.beanId = beanId;
        this.sellingPrice = sellingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
        this.isActive = isActive;
    }

    public SellOrder(int sellOrderId, int sellingInvestorId, int beanId, BigDecimal sellingPrice, Long availableAmount, Long totalAmount, boolean isActive, OffsetDateTime orderDate){
        this.sellOrderId = sellOrderId;
        this.sellingInvestorId = sellingInvestorId;
        this.beanId = beanId;
        this.sellingPrice = sellingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.isActive = isActive;
    }

    public int getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(int sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public int getSellingInvestorId() {
        return sellingInvestorId;
    }

    public void setSellingInvestorId(int sellingInvestorId) {
        this.sellingInvestorId = sellingInvestorId;
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

    public Long getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Long availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OffsetDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(OffsetDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}