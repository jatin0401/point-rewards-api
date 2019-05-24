package com.study.reward;

import com.study.reward.model.CustomerReward;
import com.study.reward.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @RequestMapping("/reward-info/all")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<List<CustomerReward>> getRewardInfo() {
        ResponseEntity<List<CustomerReward>> response = null;
        try {
            response = new ResponseEntity<>(rewardService.getRewards(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  response;
    }

}
