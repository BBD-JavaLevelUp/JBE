package com.jbe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int investorId;

    private String name;

    private String saId;

    private String email;

    public Investor(String name, String saId, String email){
        this.name = name;
        this.saId = saId;
        this.email = email;
    }

    public Investor(int investorId, String name, String saId, String email){
        this.investorId = investorId;
        this.name = name;
        this.saId = saId;
        this.email = email;
    }

    public int getInvestorId() {
        return investorId;
    }

    public void setInvestorId(int investorId) {
        this.investorId = investorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaId() {
        return saId;
    }

    public void setSaID(String saId) {
        this.saId = saId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
