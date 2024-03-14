package com.jbe.server.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity
@Table
public class BuyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buyOrderId;
    private int investorId;
    private int beanId;

    private BigDecimal price;

    private long availableAmount;

    private long totalAmount;
    private OffsetDateTime orderDate;
    private boolean isActive;
    @Transient
    private String beanName;

    public BuyOrder(){

    }

    public BuyOrder(BuyOrder buyOrder){
        this.buyOrderId = buyOrder.getBuyOrderId();
        this.investorId = buyOrder.getInvestorId();
        this.beanId = buyOrder.getBeanId();
        this.price = buyOrder.getPrice();
        this.availableAmount = buyOrder.getAvailableAmount();
        this.totalAmount = buyOrder.getTotalAmount();
        this.orderDate = buyOrder.getOrderDate();
        this.isActive = buyOrder.isActive();
    }

    public BuyOrder(int buyingInvestorId, int beanId, BigDecimal buyingPrice, long availableAmount, long totalAmount, boolean isActive){
        this.investorId = buyingInvestorId;
        this.beanId = beanId;
        this.price = buyingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
        this.isActive = isActive;
    }

    public BuyOrder(int buyOrderId, int buyingInvestorId, int beanId, BigDecimal buyingPrice, long availableAmount, long totalAmount, boolean isActive){
        this.buyOrderId = buyOrderId;
        this.investorId = buyingInvestorId;
        this.beanId = beanId;
        this.price = buyingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
        this.isActive = isActive;
    }

    public BuyOrder(int buyOrderId, int buyingInvestorId, int beanId, BigDecimal buyingPrice, long availableAmount, long totalAmount, boolean isActive, OffsetDateTime orderDate){
        this.buyOrderId = buyOrderId;
        this.investorId = buyingInvestorId;
        this.beanId = beanId;
        this.price = buyingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.isActive = isActive;
    }

    public int getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(int buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public int getInvestorId() {
        return investorId;
    }

    public void setInvestorId(int buyingInvestorId) {
        this.investorId = buyingInvestorId;
    }

    public int getBeanId() {
        return beanId;
    }

    public void setBeanId(int beanId) {
        this.beanId = beanId;
    }

    public BigDecimal getPrice() {
        if (price==null){
            return BigDecimal.ZERO;
        }
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPrice(BigDecimal buyingPrice) {
        this.price = buyingPrice;
    }

    public long getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(long availableAmount) {
        this.availableAmount = availableAmount;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
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

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}