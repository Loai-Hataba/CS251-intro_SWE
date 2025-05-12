package com.budgetapp.system;

import java.util.List;

import com.budgetapp.transaction.ExpenseManager;
import com.budgetapp.transaction.IncomeManager;
import com.budgetapp.user.AuthenticationManager;

public class BudgetSystem {
    private String currentUUID = "";

    public BudgetSystem(){
        System.out.println("System has been created!");
    }

    public boolean authenticate(String userName, String password){
        currentUUID = AuthenticationManager.getInstance().authenticateUser(userName, password);
        System.out.println("testing: " + currentUUID);
        if (currentUUID == "")
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

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Income
    public List<String> fetchIncome(){
        List<String> incomesList = IncomeManager.getInstance().summary(currentUUID);
        return incomesList;
    }

    public boolean addIncome(String source, double amount, String category, String date, int isRecurring){
        // call income manager to add income transaction in database
        boolean recurring = (isRecurring != 0);
        boolean addedIncome = IncomeManager.getInstance().add(currentUUID, source, amount, category, date, recurring);
        return addedIncome;
    }

    public boolean editIncome(String source, int incomeId, double amount, String category, String date, int isRecurring){
        boolean recurring = (isRecurring != 0);
        boolean editedIncome = IncomeManager.getInstance().edit(currentUUID, incomeId, source, amount, category, date, recurring);
        return editedIncome;
    }

    public boolean deleteIncome(int incomeId){
        boolean deletedIncome = IncomeManager.getInstance().remove(currentUUID, incomeId);
        return deletedIncome;
    }
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Expense
    public List<String> fetchExpense(){
        List<String> expenseList = ExpenseManager.getInstance().summary(currentUUID);
        return expenseList;
    }

     public boolean addExpense(String source, double amount, String category, String date, int isRecurring){
        boolean recurring = (isRecurring != 0);
        boolean addedIncome = ExpenseManager.getInstance().add(currentUUID, source, amount, category, date, recurring);
        return addedIncome;
    }

    public boolean editExpense(String source, int expenseId, double amount, String category, String date, int isRecurring){
        boolean recurring = (isRecurring != 0);
        boolean editedIncome = ExpenseManager.getInstance().edit(currentUUID, expenseId, source, amount, category, date, recurring);
        return editedIncome;
    }

    public boolean deleteExpense(int expenseId){
        boolean deletedExpense = ExpenseManager.getInstance().remove(currentUUID, expenseId);
        return deletedExpense;
    }
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Budget
    public boolean addBudget(String category, float amount){
        // call income manager to add budget in database
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Reminder
    public boolean addReminder(String reminderName, String date, String time){
        return true; 
    }

}
