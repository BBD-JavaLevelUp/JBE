package com.jbe.client.Models;

public class InventoryItem {
    private int beanId;
    private String name;
    private long amount;

    public InventoryItem(int beanId, String name, long amount) {
        this.beanId = beanId;
        this.name = name;
        this.amount = amount;
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
}
