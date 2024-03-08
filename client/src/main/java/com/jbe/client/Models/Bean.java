package com.jbe.client.Models;

import java.math.BigDecimal;

public class Bean {
    private int beanId;
    private String name;
    private BigDecimal defaultPrice;

    // Constructor
    public Bean(int beanId, String name, BigDecimal defaultPrice) {
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

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

}
