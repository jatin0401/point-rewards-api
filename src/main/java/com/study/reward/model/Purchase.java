package com.study.reward.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Purchase implements Serializable {
    private long purchaseInfoId;
    private String purchaseDesc;
    private Date purchaseDate;
    @JsonIgnore
    private Customer customer;
    private BigDecimal purchaseAmount;
    private Integer points;

    public long getPurchaseInfoId() {
        return purchaseInfoId;
    }

    public void setPurchaseInfoId(long purchaseInfoId) {
        this.purchaseInfoId = purchaseInfoId;
    }

    public String getPurchaseDesc() {
        return purchaseDesc;
    }

    public void setPurchaseDesc(String purchaseDesc) {
        this.purchaseDesc = purchaseDesc;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
