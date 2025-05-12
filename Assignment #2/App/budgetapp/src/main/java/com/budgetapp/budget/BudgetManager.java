package com.budgetapp.budget;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BudgetManager {
    private  static BudgetManager instance ;

    private BudgetManager(){
    }
    public static BudgetManager getInstance(){
        if(instance == null){
            instance = new BudgetManager();
        }
        return instance;
    }

    public boolean add(String UUID, String title, Date startDate, Date endDate, double amount, String category){

        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        // Prepare a list to add
        List<Budget> newBudgets = new ArrayList<>();
        Budget budget = new Budget(UUID, userRecord.budget.size() + 1 ,title, startDate, endDate, amount, category);
        newBudgets.add(budget);

        // If the user already has a Budget list, merge it
        if (userRecord.budget != null) {
            newBudgets.addAll(0, userRecord.budget); // add existing Budgets at the start
        }

        // Now update the field with the new list
        return Methods.updateRecordField(UUID, "budget", newBudgets);
    }

    public boolean delete(String UUID , int id ) {
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        List<Budget> currenBudgets = userRecord.budget;
        // Validate the user record
        if (currenBudgets.isEmpty()) {
            System.out.println("Budget list for UUID: " + UUID + " is empty");
            return false;
        }
        if(id > currenBudgets.size()){
            System.out.println("The entered id is greater than the number of records in the Budget list");
            return false;
        }

        // The main logic of deleting :
        for (Budget Budget : currenBudgets) {
            if (Budget.getId() == id) {
                currenBudgets.remove(Budget);
                break;
            }
        }
        // insert the new list into the user record
        return Methods.updateRecordField(UUID, "Budget", currenBudgets);
    }
    public boolean update(String UUID, int id  ,String title, Date startDate, Date endDate, double amount, String category){
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        List<Budget> currentBudgets = userRecord.budget;
        // Validate the user record
        if (currentBudgets.isEmpty()) {
            System.out.println("Budget list for UUID: " + UUID + " is empty");
            return false;
        }
        if(id > currentBudgets.size()){
            System.out.println("The entered id is greater than the number of records in the Budget list");
            return false;
        }
        // The main logic of editing :
        for (Budget budget : currentBudgets) {
            if (budget.getId() == id) {
                budget.setTitle(title);
                budget.setStartDate(startDate);
                budget.setEndDate(endDate);
                budget.setAmount(amount);
                budget.setCategory(category);
                break;
            }
        }
        return Methods.updateRecordField(UUID, "budget", currentBudgets);
    }

    public List<String> summary(String UUID){
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return null;
        }
        List<Budget> currentBudgets = userRecord.budget;
        List<String> summaries = new ArrayList<>();
        for (Budget Budget : currentBudgets) {
            summaries.add(Budget.getSummary());
        }
        return summaries;
    }

}
