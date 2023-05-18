package com.example.houserentalmanagement.Model;

public class Owner {
    String email;
    public Owner() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Owner(String email, String phoneNo, String id, String username, String password) {
        this.email = email;
        this.phoneNo = phoneNo;
        this.id = id;
        this.username = username;
        this.password = password;
    }

    String phoneNo;
    String id;
    String username;
    String password;
}