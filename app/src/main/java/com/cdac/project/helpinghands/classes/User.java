package com.cdac.project.helpinghands.classes;

import java.io.Serializable;

/**
 * Created by parag on 20-07-2016.
 */
public class User implements Serializable {
    private int userID;
    private String userName;
    private String name;
    private String email;
    private String address;
    private String mobile;
    private String password;

    public User(){};
    public User(int userID, String userName, String name, String email, String address, String mobile, String password) {
        this.userID = userID;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
