package com.study.reward.service;

import com.study.reward.model.CustomerReward;
import com.study.reward.model.Purchase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface RewardService {
    List<CustomerReward> getRewards() throws FileNotFoundException, IOException, ParseException;
    List<Purchase> getAllPurchases() throws FileNotFoundException, IOException, ParseException;
}
