package com.jbe.client.Models;

public class Investor {
    private int investorId;
    private String name;
    private String sAId;
    private String email;

    // constructor
    public Investor(int investorId, String name, String sAId, String email) {
        this.investorId = investorId;
        this.name = name;
        this.sAId = sAId;
        this.email = email;
    }

    // Getters and setters
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

    public String getSAId() {
        return sAId;
    }

    public void setSAId(String sAId) {
        this.sAId = sAId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
