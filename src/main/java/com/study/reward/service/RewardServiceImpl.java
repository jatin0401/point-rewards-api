package com.study.reward.service;

import com.study.reward.model.*;
import com.study.reward.util.DataHeader;
import com.study.reward.util.RewardsPointCalculator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {
    private String datePattern = "MM/dd/yyyy";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
    RewardsPointCalculator rewardsPointCalculator = new RewardsPointCalculator();

    @Override
    public List<CustomerReward> getRewards() throws FileNotFoundException, IOException, ParseException {
        List<CustomerReward> rewardList = new ArrayList<CustomerReward>();
        List<Purchase> purchaseList = getAllPurchases();
        Set<Long> customerSet = purchaseList
                                .stream()
                                .map(data -> data.getCustomer().getCustomerId())
                                .collect(Collectors.toSet());
        long month1 = LocalDate.now().getMonth().getValue();
        long month2 = LocalDate.now().minusMonths(1).getMonth().getValue();
        long month3 = LocalDate.now().minusMonths(2).getMonth().getValue();

        for(long customerId : customerSet){
            CustomerReward customerReward = new CustomerReward();
            List<MonthCustomerReward> monthCustomerRewards = new ArrayList<>();
            List<Purchase> totalPurchaseList = purchaseList
                                                .stream()
                                                .filter(data -> data.getCustomer().getCustomerId() == customerId)
                                                .collect(Collectors.toList());
            List<Purchase> purchases1 = totalPurchaseList
                                        .stream()
                                        .filter(data -> data.getPurchaseDate().getMonth()+1 == month1)
                                        .collect(Collectors.toList());
            int purchase1Points = purchases1.stream().map(e-> e.getPoints()).reduce(0,Integer:: sum);
            MonthCustomerReward monthCustomerReward = new MonthCustomerReward();
            if(!purchases1.isEmpty()){
                monthCustomerReward.setMonthName(getMonthName(purchases1.get(0).getPurchaseDate().getMonth()));
                monthCustomerReward.setTotalMonthPoints(purchase1Points);
                monthCustomerReward.setPurchases(purchases1);
                monthCustomerRewards.add(monthCustomerReward);
            }

            List<Purchase> purchases2 = totalPurchaseList
                    .stream()
                    .filter(data -> data.getPurchaseDate().getMonth()+1 == month2)
                    .collect(Collectors.toList());
            int purchase2Points = purchases2.stream().map(e-> e.getPoints()).reduce(0,Integer:: sum);
            monthCustomerReward = new MonthCustomerReward();
            if(!purchases2.isEmpty()){
                monthCustomerReward.setMonthName(getMonthName(purchases2.get(0).getPurchaseDate().getMonth()));
                monthCustomerReward.setTotalMonthPoints(purchase2Points);
                monthCustomerReward.setPurchases(purchases2);
                monthCustomerRewards.add(monthCustomerReward);
            }

            List<Purchase> purchases3 = totalPurchaseList
                    .stream()
                    .filter(data -> data.getPurchaseDate().getMonth()+1 == month3)
                    .collect(Collectors.toList());
            int purchase3Points = purchases3.stream().map(e-> e.getPoints()).reduce(0,Integer:: sum);
            monthCustomerReward = new MonthCustomerReward();
            if(!purchases3.isEmpty()){
                monthCustomerReward.setMonthName(getMonthName(purchases3.get(0).getPurchaseDate().getMonth()));
                monthCustomerReward.setTotalMonthPoints(purchase3Points);
                monthCustomerReward.setPurchases(purchases3);
                monthCustomerRewards.add(monthCustomerReward);
            }

            customerReward.setMonthCustomerRewards(monthCustomerRewards);

            int totalPoints = totalPurchaseList.stream().map(e-> e.getPoints()).reduce(0,Integer:: sum);
            customerReward.setTotalPoints(totalPoints);
            customerReward.setCustomer(totalPurchaseList.get(0).getCustomer());
            rewardList.add(customerReward);
        }
        return rewardList;
    }

    @Override
    public List<Purchase> getAllPurchases() throws FileNotFoundException, IOException, ParseException {
        List<Purchase> purchaseList = new ArrayList<Purchase>();
        Purchase purchase;
        Customer customer;
        Address address;
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("data3.csv").getFile());
        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

        for(CSVRecord record : records){
            purchase = new Purchase();
            customer = new Customer();
            address =  new Address();

            address.setAddressLine1(record.get(DataHeader.ADDRESSLINE1));
            address.setAddressLine2(record.get(DataHeader.ADDRESSLINE2));
            address.setState(record.get(DataHeader.STATE));
            address.setCity(record.get(DataHeader.CITY));
            address.setZipCode(record.get(DataHeader.ZIPCODE));

            customer.setFirstName(record.get(DataHeader.FIRSTNAME));
            customer.setMiddleName(record.get(DataHeader.MIDDLENAME));
            customer.setLastName(record.get(DataHeader.LASTNAME));
            customer.setCustomerId(Long.valueOf(record.get(DataHeader.CUSTOMERID)));
            customer.setAddress(address);

            purchase.setPurchaseInfoId(Long.valueOf(record.get(DataHeader.PURCHASEINFOID)));
            purchase.setPurchaseDesc(record.get(DataHeader.PURCHASEDESC));
            purchase.setPurchaseAmount(new BigDecimal(record.get(DataHeader.PURCHASEAMOUNT)));
            purchase.setPurchaseDate(simpleDateFormat.parse(record.get(DataHeader.PURCHASEDATE)));
            purchase.setCustomer(customer);
            purchase.setPoints(rewardsPointCalculator.calculateRewardsPoint(purchase.getPurchaseAmount()));

            purchaseList.add(purchase);
        }

        return purchaseList;
    }
    public static String getMonthName(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }
}
