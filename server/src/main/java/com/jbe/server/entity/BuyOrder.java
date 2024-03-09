package com.jbe.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity
public class BuyOrder {
    @Id
    @GeneratedValue
    private int buyOrderId;
    private int buyingInvestorId;
    private int beanId;

    private BigDecimal buyingPrice;

    private long availableAmount;

    private long totalAmount;
    private OffsetDateTime orderDate;
    private boolean isActive;

    public BuyOrder(int buyingInvestorId, int beanId, BigDecimal buyingPrice, Long availableAmount, Long totalAmount, boolean isActive){
        this.buyingInvestorId = buyingInvestorId;
        this.beanId = beanId;
        this.buyingPrice = buyingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
        this.isActive = isActive;
    }

    public BuyOrder(int buyOrderId, int buyingInvestorId, int beanId, BigDecimal buyingPrice, Long availableAmount, Long totalAmount, boolean isActive){
        this.buyingInvestorId = buyingInvestorId;
        this.beanId = beanId;
        this.buyingPrice = buyingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = OffsetDateTime.now(ZoneId.of("Africa/Johannesburg"));
        this.isActive = isActive;
    }

    public BuyOrder(int buyOrderId, int buyingInvestorId, int beanId, BigDecimal buyingPrice, Long availableAmount, Long totalAmount, boolean isActive, OffsetDateTime orderDate){
        this.buyOrderId = buyOrderId;
        this.buyingInvestorId = buyingInvestorId;
        this.beanId = beanId;
        this.buyingPrice = buyingPrice;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.isActive = isActive;
    }

    public int getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderID(int buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public int getBuyingInvestorId() {
        return buyingInvestorId;
    }

    public void setBuyingInvestorId(int buyingInvestorId) {
        this.buyingInvestorId = buyingInvestorId;
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

    public void setSellingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
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
