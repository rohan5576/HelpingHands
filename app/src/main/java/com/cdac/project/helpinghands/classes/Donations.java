package com.cdac.project.helpinghands.classes;

import java.io.Serializable;

/**
 * Created by parag on 20-07-2016.
 */
public class Donations implements Serializable {

    private int donateId;
    private String foodDesc;
    private String foodValue;
    private String availDate;
    private String expiryDate;
    private String userId;
    private String address;
    private String availTime;
    private String expiryTime;
   private  String status;


    public Donations(int donateId, String foodDesc, String foodValue, String availDate, String expiryDate, String userId, String address, String availTime, String expiryTime, String status) {
        this.donateId = donateId;
        this.foodDesc = foodDesc;
        this.foodValue = foodValue;
        this.availDate = availDate;
        this.expiryDate = expiryDate;
        this.userId = userId;
        this.address = address;
        this.availTime = availTime;
        this.expiryTime = expiryTime;
        this.status = status;
    }

    public int getDonateId() {
        return donateId;
    }

    public void setDonateId(int donateId) {
        this.donateId = donateId;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public String getFoodValue() {
        return foodValue;
    }

    public void setFoodValue(String foodValue) {
        this.foodValue = foodValue;
    }

    public String getAvailDate() {
        return availDate;
    }

    public void setAvailDate(String availDate) {
        this.availDate = availDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvailTime() {
        return availTime;
    }

    public void setAvailTime(String availTime) {
        this.availTime = availTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Donations{" +
                "donateId=" + donateId +
                ", foodDesc='" + foodDesc + '\'' +
                ", foodValue='" + foodValue + '\'' +
                ", availDate='" + availDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", userId='" + userId + '\'' +
                ", address='" + address + '\'' +
                ", availTime='" + availTime + '\'' +
                ", expiryTime='" + expiryTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
