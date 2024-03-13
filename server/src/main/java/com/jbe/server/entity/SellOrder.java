package com.jbe.server.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity
@Table
public class SellOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sellOrderId;
    private int investorId;
    private int beanId;

    private BigDecimal price;

    private long availableAmount;

    private long totalAmount;
    private OffsetDateTime orderDate;
    private boolean isActive;

    public SellOrder(){

    }
    public SellOrder(int sellingInvestorId, int beanId, BigDecimal sellingPrice, Long availableAmount, Long totalAmount, boolean isActive){
        this.investorId = sellingInvestorId;
        this.beanId = beanId;
        this.price = sellingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
        this.isActive = isActive;
    }

    public SellOrder(int sellOrderId, int sellingInvestorId, int beanId, BigDecimal sellingPrice, Long availableAmount, Long totalAmount, boolean isActive){
        this.sellOrderId = sellOrderId;
        this.investorId = sellingInvestorId;
        this.beanId = beanId;
        this.price = sellingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
        this.isActive = isActive;
    }

    public SellOrder(int sellOrderId, int sellingInvestorId, int beanId, BigDecimal sellingPrice, Long availableAmount, Long totalAmount, boolean isActive, OffsetDateTime orderDate){
        this.sellOrderId = sellOrderId;
        this.investorId = sellingInvestorId;
        this.beanId = beanId;
        this.price = sellingPrice;
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

    public int getInvestorId() {
        return investorId;
    }

    public void setInvestorId(int sellingInvestorId) {
        this.investorId = sellingInvestorId;
    }

    public int getBeanId() {
        return beanId;
    }

    public void setBeanId(int beanId) {
        this.beanId = beanId;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPrice(BigDecimal sellingPrice) {
        this.price = sellingPrice;
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