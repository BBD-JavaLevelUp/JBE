package com.jbe.server.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;
    private int investorId;
    private int beanId;
    @Transient
    private String beanName;
    private Long amount;
    @Transient
    private BigDecimal profit;

    public Inventory(){

    }

    public Inventory(Inventory inventory){
        this.inventoryId = inventory.getInventoryId();
        this.investorId = inventory.getInvestorId();
        this.beanId = inventory.getBeanId();
        this.amount = inventory.getAmount();
    }

    public Inventory(int investorId, int beanId, Long amount){
        this.investorId = investorId;
        this.beanId = beanId;
        this.amount = amount;
    }

    public Inventory(int inventoryId, int investorId, int beanId, Long amount){
        this.inventoryId = inventoryId;
        this.investorId = investorId;
        this.beanId = beanId;
        this.amount = amount;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}