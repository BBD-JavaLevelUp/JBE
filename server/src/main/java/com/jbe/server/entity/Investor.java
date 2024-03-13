package com.jbe.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int investorId;
    private String name;
    private String saId;
    private String email;

    public Investor() {
    }

    public Investor(String name, String saId, String email) {
        this.name = name;
        this.saId = saId;
        this.email = email;
    }

    public int getInvestorId() {
        return investorId;
    }

    public String getName() {
        return name;
    }

    public String getSaId() {
        return saId;
    }

    public String getEmail() {
        return email;
    }

    public void setInvestorId(int investorId) {
        this.investorId = investorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSaId(String SaId) {
        this.saId = SaId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Investor{" +
                "investorId=" + investorId +
                ", name='" + name + '\'' +
                ", saId='" + saId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
