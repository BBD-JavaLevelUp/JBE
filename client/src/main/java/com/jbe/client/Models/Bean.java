package com.jbe.client.Models;

public class Bean {
    private int beanId;
    private String name;
    private double defaultPrice;

    // Constructor
    public Bean(int beanId, String name, double defaultPrice) {
        this.beanId = beanId;
        this.name = name;
        this.defaultPrice = defaultPrice;
    }

    // Getters and Setters
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

    public double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

}
