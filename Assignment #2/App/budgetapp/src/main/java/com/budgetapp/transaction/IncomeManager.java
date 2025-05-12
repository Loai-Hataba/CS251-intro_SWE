package com.budgetapp.transaction;

import com.budgetapp.methods.Methods;
import java.util.ArrayList;
import java.util.List;
import com.budgetapp.database.Records;

public class IncomeManager implements  ITransactionManager{

    // The Attributes :
    private static IncomeManager instance ;
    // The Constructor
    private IncomeManager(){

    }

    // Function to access the instance :
    public static IncomeManager getInstance() {
        if (instance == null) {
            instance = new IncomeManager();
        }
        return instance;
    }

    // The interface functions :
    @Override
    public boolean add (String UUID, String source ,double amount, String category, String date, boolean isRecurring ){

        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        // Prepare a list to add
        List<Income> newIncomeList = new ArrayList<>();
        int size;
        if (userRecord.income == null){
            size = 1;
        } else size = userRecord.income.size() + 1;
        Income income = new Income(UUID, size ,source, amount, category, date, isRecurring);
        newIncomeList.add(income);

        // If the user already has an income list, merge it
        if (userRecord.income != null) {
            newIncomeList.addAll(0, userRecord.income); // add existing incomes at the start
        }

        // Now update the field with the new list
        return Methods.updateRecordField(UUID, "income", newIncomeList);
    }


    //! adjust remaining ids of records
    @Override
    public boolean remove( String UUID , int id ) {
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        List<Income> currentIncomes = userRecord.income;
        // Validate the user record
        if (currentIncomes.isEmpty()) {
            System.out.println("Income list for UUID: " + UUID + " is empty");
            return false;
        }
        if(id > currentIncomes.size()){
            System.out.println("The entered id is greater than the number of records in the income list");
            return false;
        }

        // The main logic of deleting :
        for (Income income : currentIncomes) {
            if (income.getId() == id) {
                currentIncomes.remove(income);
                break;
            }
        }
        // insert the new list into the user record
        return Methods.updateRecordField(UUID, "income", currentIncomes);
        

    }

    @Override
    public boolean edit(String UUID , int id , String source ,double amount, String category, String date, boolean isRecurring ) {
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        List<Income> currentIncomes = userRecord.income;
        // Validate the user record
        if (currentIncomes.isEmpty()) {
            System.out.println("Income list for UUID: " + UUID + " is empty");
            return false;
        }
        if(id > currentIncomes.size()){
            System.out.println("The entered id is greater than the number of records in the income list");
            return false;
        }
        // The main logic of editing :
        for (Income income : currentIncomes) {
            if (income.getId() == id) {
                income.setTitle(source);
                income.setAmount(amount);
                income.setCategory(category);
                income.setDate(date);
                income.markAsRecurring(isRecurring);
                break;
            }
        }
        return Methods.updateRecordField(UUID, "income", currentIncomes);
    }

    @Override
    public List<String> summary(String UUID) {
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return null;
        }
        List<Income> currentIncomes = userRecord.income;
        if (currentIncomes == null)
        {
            List<String> emptyList = new ArrayList<>();
            return emptyList;
        }
        List<String> summaries = new ArrayList<>();
            for (Income income : currentIncomes) {
                summaries.add(income.getSummary());
            }
        return summaries;
    }
}
