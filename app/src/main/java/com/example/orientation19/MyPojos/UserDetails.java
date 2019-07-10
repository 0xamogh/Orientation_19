package com.example.orientation19.MyPojos;

public class UserDetails {

    private String loginStatus;
    private String JWTToken;
    private String rollNumber;
    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getJWTToken() {
        return JWTToken;
    }

    public void setJWTToken(String JWTToken) {
        this.JWTToken = JWTToken;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
}
