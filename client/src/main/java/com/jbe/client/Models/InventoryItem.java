package com.jbe.client.Models;

import java.math.BigDecimal;

public class InventoryItem {
    private int beanId;
    private String name;
    private long amount;
    private BigDecimal defaultPrice;

    public InventoryItem(int beanId, String name, long amount, BigDecimal defaultPrice) {
        this.beanId = beanId;
        this.name = name;
        this.amount = amount;
        this.defaultPrice = defaultPrice;
    }

    public int getBeanId() {
        return beanId;
    }

    public void setBeanId(int beanId) {
        this.beanId = beanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }
    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
