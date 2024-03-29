package com.example.tcrs_group8.Models;

public class DataModel {
    private String amount,Id;

    public String getid() {
        return Id;
    }
    public String getAmount() {
        return amount;
    }

    public void setData(String id,String Amount) {
        this.Id = id;
        this.amount = Amount;
    }

}
