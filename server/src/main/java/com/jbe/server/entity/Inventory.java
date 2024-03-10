package com.jbe.server.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;
    private int investorId;
    private int beanId;
    private Long amount;

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
}