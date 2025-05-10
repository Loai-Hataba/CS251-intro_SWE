package com.budgetapp.user;

import java.util.ArrayList;
import java.util.Date;

public class User {

    private String userId ;
    private String userName ;
    private String password  ;
    private String country ;
    private String email  ;
    private String phoneNumber ;
    private ArrayList<BankAccount> bankAccounts ;

    public User(String userId, String userName, String password, String email ,String Country, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.country = Country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        bankAccounts = new ArrayList();
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {this.userId = userId;}
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    //! function to add a bank account
    public boolean addBankAccount(String bankName , String accountNum , String holderName) {
        BankAccount bankAccount = new BankAccount(bankName , accountNum , holderName);
        if (bankAccounts.contains(bankAccount)) {
            System.out.println("Bank account is already in the list");
            return false ;
        }
        bankAccounts.add(bankAccount);
        return true;
    }
    //! function to unlink an bankAccount
    public boolean removeBankAccount(String accountNumber) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                bankAccounts.remove(account);
                return true;
            }
        }
        System.out.println("Bank account is not in the list");
        return false;
    }

    //!
    public boolean signup(String userName , String email ,String password)
    {
        // for now return true cuz we are right
        return true;
    }
    //!

    public boolean login (String userName , String password) {
        // for now return ture cuz we are right
        return true;
    }
    //!

    public boolean logout() {
        // for now return ture cuz we are right
        return true;
    }
    //!

    public void incomeView () {
        return ;
    }
    public boolean addIncome (String incomeSrc , float amount) {
        // for now return true cuz we are right
        return true;
    }
    //!

    public void budgetView () {
        return ;
    }
    //!

    public boolean addBudget (String budgetSrc , float amount) {
        // for now return true cuz we are right
        return true;
    }
    //!

    public boolean addReminder (String reminderTitle , Date date , float amount) {
        // for now return true cuz we are right
        return true;
    }
    //!

    public void expenseView () {
        return ;
    }
    //!

    public boolean addExpense (String category , float amount , Date date ) {
        return true ;
    }


}
