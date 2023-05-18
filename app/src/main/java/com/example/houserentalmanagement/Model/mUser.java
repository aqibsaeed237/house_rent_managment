package com.example.houserentalmanagement.Model;

public class mUser {
    String username;
    String email;

    public mUser(){

    }
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public mUser(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    String phoneNo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public mUser(String username, String email, String password, String id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    String password;
    String id;
}
