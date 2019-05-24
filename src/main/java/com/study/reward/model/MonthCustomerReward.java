package com.study.reward.model;

import java.io.Serializable;
import java.util.List;

public class MonthCustomerReward implements Serializable {
    private String monthName;
    private List<Purchase> purchases;
    private Integer totalMonthPoints;

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Integer getTotalMonthPoints() {
        return totalMonthPoints;
    }

    public void setTotalMonthPoints(Integer totalMonthPoints) {
        this.totalMonthPoints = totalMonthPoints;
    }
}
