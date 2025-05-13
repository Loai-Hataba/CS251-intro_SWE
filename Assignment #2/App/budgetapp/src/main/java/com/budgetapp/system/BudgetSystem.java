package com.budgetapp.system;

import java.util.List;

import com.budgetapp.budget.BudgetManager;
import com.budgetapp.reminder.ReminderManager;
import com.budgetapp.transaction.ExpenseManager;
import com.budgetapp.transaction.IncomeManager;
import com.budgetapp.user.AuthenticationManager;

public class BudgetSystem {

    private String currentUUID = "";

    public BudgetSystem() {
    }

    public void clearUUID(){
        currentUUID = "";
    }

    public boolean authenticate(String userName, String password) {
        currentUUID = AuthenticationManager.getInstance().authenticateUser(userName, password);
        if (currentUUID.equals("")) {
            return false;
        }
        System.out.println("\nUser " + userName + " has been authenticated");
        return true;
    }

    public boolean register(String userName, String password, String phoneNum, String email, String country) {
        // send to auth manager -> auth manager send otp -> otp tmam -> auth manager send to user manager -> user manager create user in database
        currentUUID = AuthenticationManager.getInstance().registerUser(userName, password, email, phoneNum, country);

        if (currentUUID.equals("")) {
            return false;
        }
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Income
    public List<String> fetchIncome() {
        return IncomeManager.getInstance().summary(currentUUID);
    }

    public boolean addIncome(String source, double amount, String category, String date, int isRecurring) {
        // call income manager to add income transaction in database
        boolean recurring = (isRecurring != 0);
        return IncomeManager.getInstance().add(currentUUID, source, amount, category, date, recurring);
    }

    public boolean editIncome(String source, int incomeId, double amount, String category, String date, int isRecurring) {
        boolean recurring = (isRecurring != 0);
        return IncomeManager.getInstance().edit(currentUUID, incomeId, source, amount, category, date, recurring);
    }

    public boolean deleteIncome(int incomeId) {
        return IncomeManager.getInstance().remove(currentUUID, incomeId);
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Expense
    public List<String> fetchExpense() {
        return ExpenseManager.getInstance().summary(currentUUID);
    }

    public boolean addExpense(String source, double amount, String category, String date, int isRecurring) {
        boolean recurring = (isRecurring != 0);
        return ExpenseManager.getInstance().add(currentUUID, source, amount, category, date, recurring);
    }

    public boolean editExpense(String source, int expenseId, double amount, String category, String date, int isRecurring) {
        boolean recurring = (isRecurring != 0);
        return ExpenseManager.getInstance().edit(currentUUID, expenseId, source, amount, category, date, recurring);
    }

    public boolean deleteExpense(int expenseId) {
        return ExpenseManager.getInstance().remove(currentUUID, expenseId);
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Budget
    public List<String> fetchBudget() {
        return BudgetManager.getInstance().summary(currentUUID);
    }

    public boolean addBudget(String source, double amount, String category, String startDate, String endDate) {
        return BudgetManager.getInstance().add(currentUUID, source, amount, category, startDate, endDate);
    }

    public boolean editBudget(String source, int budgetId, double amount, String category, String startDate, String endDate) {
        return BudgetManager.getInstance().edit(currentUUID, budgetId, source, amount, category, startDate, endDate);
    }

    public boolean deleteBudget(int budgetId) {
        return BudgetManager.getInstance().remove(currentUUID, budgetId);
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Reminder
    public List<String> fetchReminder() {
        return ReminderManager.getInstance().summary(currentUUID);
    }

    public boolean addReminder(String title, String description, String date, String time) {
        return ReminderManager.getInstance().add(currentUUID, title, description, date, time);
    }

    public boolean editReminder(String title, int reminderId, String description, String date, String time) {
        return ReminderManager.getInstance().edit(currentUUID, reminderId, title, description, date, time);
    }

    public boolean deleteReminder(int reminderId) {
        return ReminderManager.getInstance().delete(currentUUID, reminderId);
    }

}
