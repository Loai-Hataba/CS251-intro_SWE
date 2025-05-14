package com.budgetapp.user;


public class User {

    private final String userId;
    private final String userName;
    private String password;
    private String country;
    private String email;
    private String phoneNumber;

    public User(String userId, String userName, String password, String email, String Country, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.country = Country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        // bankAccounts = new ArrayList();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
