package com.jbe.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class BuyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int investorId;
    private int beanId;
    private Long buyingPrice;
    private int availableAmount;
    private int totalAmount;
    private Date buyOrderDate;
    private boolean isActive;

    public int getId() {
        return id;
    }
}
