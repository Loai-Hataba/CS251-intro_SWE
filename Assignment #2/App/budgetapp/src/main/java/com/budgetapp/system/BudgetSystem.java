package com.budgetapp.system;

import com.budgetapp.user.AuthenticationManager;
import com.budgetapp.transaction.*;
import java.util.List;
import com.budgetapp.database.Records;


public class BudgetSystem {
    private String currentUUID = "";

    public BudgetSystem(){
        System.out.println("System has been created!");
    }

    public boolean authenticate(String userName, String password){
        currentUUID = AuthenticationManager.getInstance().authenticateUser(userName, password);
        if (currentUUID != "")
        {
            return false;
        }
        return true;
    }

    public boolean register(String userName, String password, String phoneNum, String email, String country)
    {
        // send to auth manager -> auth manager send otp -> otp tmam -> auth manager send to user manager -> user manager create user in database
        currentUUID = AuthenticationManager.getInstance().registerUser(userName, password, email, phoneNum, country);
        if (currentUUID != "")
        {
            return false;
        }
        return true;
    }

    public List<String> fetchIncome(){
        List<String> incomesList = IncomeManager.getInstance().summary();
        return incomesList;
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
