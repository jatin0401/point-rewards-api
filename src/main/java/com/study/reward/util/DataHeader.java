package com.study.reward.util;

public enum DataHeader {
    CUSTOMERID("CUSTOMERID"),
    FIRSTNAME("FIRSTNAME"),
    MIDDLENAME("MIDDLENAME"),
    LASTNAME("LASTNAME"),
    ADDRESSLINE1("ADDRESSLINE1"),
    ADDRESSLINE2("ADDRESSLINE2"),
    CITY("CITY"),
    STATE("STATE"),
    ZIPCODE("ZIPCODE"),
    PURCHASEINFOID("PURCHASEINFOID"),
    PURCHASEDESC("PURCHASEDESC"),
    PURCHASEDATE("PURCHASEDATE"),
    PURCHASEAMOUNT("PURCHASEAMOUNT");

    private String name;
    private DataHeader(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static DataHeader getByName(String name){
        for(DataHeader key : DataHeader.values()){
            if(key.getName().equals(name)){
                return key;
            }
        }
        return null;
    }
}
