package com.jbe.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int beanId;
    private String name;
    private BigDecimal defaultPrice;

    public Bean() {
    }

    public Bean(int beanId, String name, BigDecimal defaultPrice) {
        this.beanId = beanId;
        this.name = name;
        this.defaultPrice = defaultPrice;
    }

    public Bean(String name, BigDecimal defaultPrice) {
        this.name = name;
        this.defaultPrice = defaultPrice;
    }

    public int getBeanId() {
        return beanId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setBeanId(int beanId) {
        this.beanId = beanId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "id=" + beanId +
                ", name='" + name + '\'' +
                ", defaultPrice=" + defaultPrice +
                '}';
    }
}