package com.budgetapp.transaction;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager  implements  ITransactionManager{
    
    private static ExpenseManager instance  ;
    
    // Make the constructor private 
    private ExpenseManager(){}
    // The function to access the instance object 
    public static ExpenseManager getInstance(){
        if(instance == null){
            instance = new ExpenseManager();
        }
        return instance;
    }
    
    @Override
    public boolean add(String UUID, String source, double amount, String category, String date, boolean isRecurring) {
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }


        // Prepare a list to add
        List<Expense> newExpneseList = new ArrayList<>();
        Expense expense = new Expense(UUID, userRecord.expense.size() + 1 ,source, amount, category, date, isRecurring);
        newExpneseList.add(expense);

        // If the user already has an expense list, merge it
        if (userRecord.expense != null) {
            newExpneseList.addAll(0, userRecord.expense); // add existing expenses at the start
        }

        // Now update the field with the new list
        return Methods.updateRecordField(UUID, "expense", newExpneseList);
    }

    @Override
    public boolean remove(String UUID, int id) {
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        List<Expense> currentExpense = userRecord.expense;
        // Validate the user record
        if (currentExpense.isEmpty()) {
            System.out.println("expense list for UUID: " + UUID + " is empty");
            return false;
        }
        if(id > currentExpense.size()){
            System.out.println("The entered id is greater than the number of records in the expense list");
            return false;
        }

        // The main logic of deleting :
        for (Expense expense : currentExpense) {
            if (expense.getId() == id) {
                currentExpense.remove(expense);
                break;
            }
        }
        // insert the new list into the user record
        return Methods.updateRecordField(UUID, "expense", currentExpense);
    }

    @Override
    public boolean edit(String UUID, int id, String source, double amount, String category, String date, boolean isRecurring) {
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        List<Expense> currentExpense = userRecord.expense;
        // Validate the user record
        if (currentExpense.isEmpty()) {
            System.out.println("expense list for UUID: " + UUID + " is empty");
            return false;
        }
        if(id > currentExpense.size()){
            System.out.println("The entered id is greater than the number of records in the expense list");
            return false;
        }
        // The main logic of editing :
        for (Expense expense : currentExpense) {
            if (expense.getId() == id) {
                expense.setTitle(source);
                expense.setAmount(amount);
                expense.setCategory(category);
                expense.setDate(date);
                expense.markAsRecurring(isRecurring);
                break;
            }
        }
        // insert the new list into the user record
        return Methods.updateRecordField(UUID, "expense", currentExpense);
    }

    @Override
    public List<String> summary(String UUID) {

        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return null;
        }
        List<Expense> currentExpenses = userRecord.expense;
        List<String> summaries = new ArrayList<>();
        for (Expense expense : currentExpenses) {
            summaries.add(expense.getSummary());
        }
        return summaries;
    }
}
