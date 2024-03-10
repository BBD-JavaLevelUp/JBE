package com.jbe.server.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Bean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Float defaultPrice;

    public Bean() {
    }

    public Bean(String name, Float defaultPrice) {
        this.name = name;
        this.defaultPrice = defaultPrice;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getDefaultPrice() {
        return defaultPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultPrice(Float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", defaultPrice=" + defaultPrice +
                '}';
    }
}