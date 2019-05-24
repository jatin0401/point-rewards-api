package com.study.reward.util;

import java.math.BigDecimal;

public class RewardsPointCalculator {
    private BigDecimal THRESHOLD_VAL1 = new BigDecimal(50);
    private BigDecimal THRESHOLD_VAL2 = new BigDecimal(100);

    public Integer calculateRewardsPoint(BigDecimal amount){
        Integer rewardsPoint = 0;

        if(amount.compareTo(THRESHOLD_VAL2)>0){
            rewardsPoint = rewardsPoint +  amount.subtract(THRESHOLD_VAL2).multiply(new BigDecimal(2)).toBigInteger().intValue();
            rewardsPoint = rewardsPoint +  THRESHOLD_VAL2.subtract(THRESHOLD_VAL1).multiply(new BigDecimal(1)).toBigInteger().intValue();
        }else if(amount.compareTo(THRESHOLD_VAL1)>0){
            rewardsPoint = rewardsPoint +  amount.subtract(THRESHOLD_VAL1).multiply(new BigDecimal(1)).toBigInteger().intValue();
        }
        return rewardsPoint;
    }
}
