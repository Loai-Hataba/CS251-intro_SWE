package com.budgetapp.system;

import com.budgetapp.user.AuthenticationManager;


public class BudgetSystem {
    public BudgetSystem(){
        System.out.println("System has been created!");
    }

    public boolean authenticate(String userName, String password){
        return true;
    }

    public boolean register(String userName, String password, String phoneNum, String email, String country)
    {
        AuthenticationManager.getInstance().registerUser(userName, password, email, phoneNum, country);
        // send to auth manager -> auth manager send otp -> otp tmam -> auth manager send to user manager -> user manager create user in database
        return true;
    }

    public boolean addIncome(String source, float amount){
        // call income manager to add income transaction in database
        return true;
    }

    public boolean addBudget(String category, float amount){
        // call income manager to add budget in database
        return true;
    }

    public boolean addReminder(String reminderName, String date, String time){
        return true; 
    }

    public boolean addExpense(String category, float amount, String date){
        return true; 
    }
}
