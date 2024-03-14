package com.jbe.server.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Entity
@Table
public class Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int beanId;
    private String name;
    private BigDecimal defaultPrice;
    @Transient
    private BigDecimal jbePrice;
    @Transient
    private BigDecimal marketPrice;

    public Bean() {
    }

    public Bean(Bean bean){
        this.beanId = bean.getBeanId();
        this.name = bean.getName();
        this.defaultPrice = bean.getDefaultPrice();
        this.jbePrice = BigDecimal.ZERO;
        this.marketPrice = BigDecimal.ZERO;
    }

    public Bean(int beanId, String name, BigDecimal defaultPrice) {
        this.beanId = beanId;
        this.name = name;
        this.defaultPrice = defaultPrice;
        this.jbePrice = BigDecimal.ZERO;
        this.marketPrice = BigDecimal.ZERO;
    }


    public Bean(String name, BigDecimal defaultPrice) {
        this.name = name;
        this.defaultPrice = defaultPrice;
        this.jbePrice = BigDecimal.ZERO;
        this.marketPrice = BigDecimal.ZERO;
    }


    public int getBeanId() {
        return beanId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice.setScale(2, RoundingMode.HALF_UP);
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

    public BigDecimal getJbePrice() {
        return jbePrice.setScale(2, RoundingMode.HALF_UP);
    }

    public void setJbePrice(BigDecimal jbePrice) {
        this.jbePrice = jbePrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
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