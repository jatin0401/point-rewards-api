package com.study.reward.model;

import java.io.Serializable;
import java.util.List;

public class CustomerReward implements Serializable {
    private Customer customer;
    private List<MonthCustomerReward> monthCustomerRewards;
    private Integer totalPoints;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<MonthCustomerReward> getMonthCustomerRewards() {
        return monthCustomerRewards;
    }

    public void setMonthCustomerRewards(List<MonthCustomerReward> monthCustomerRewards) {
        this.monthCustomerRewards = monthCustomerRewards;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }
}
