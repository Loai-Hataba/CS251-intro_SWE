package com.budgetapp.transaction;

import com.budgetapp.methods.Methods;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
// import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.budgetapp.database.Records;

public class IncomeManager implements  ITransactionManager{

    // The Attributes :
    private static IncomeManager instance ;
    private static final String INCOMES_FILE = "incomes.json";
    private final Gson gson;
    private ArrayList <Income> incomes ;

    // The Constructor
    private IncomeManager(){
        gson = new Gson();
        // Create the file if it doesn't exist
        File file = new File(INCOMES_FILE);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(incomes, writer); // Write empty array
                System.out.println("Created users.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        Income income = new Income(UUID, source, amount, category, date, isRecurring);

        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }

        // Prepare a list to add
        List<Income> newIncomeList = new ArrayList<>();
        newIncomeList.add(income);

        // If the user already has an income list, merge it
        if (userRecord.income != null) {
            newIncomeList.addAll(0, userRecord.income); // add existing incomes at the start
        }

        // Now update the field with the new list
        boolean added = Methods.updateRecordField(UUID, "income", newIncomeList);
        return added;
    }

    // @Override
    // public List<Income> fetch(){
    //     return 
    // }

    @Override
    public boolean remove(int id ) {
        return false;
    }

    @Override
    public boolean edit(int id ) {
        return false;
    }

    @Override
    public List<String> summary() {
        List<String> summaries = new ArrayList<>();
        if (incomes != null) {
            for (Income income : incomes) {
                summaries.add(income.getSummary());
            }
        }
        return summaries;
    }

    @Override
    public void saveToFile() {
        try (Writer writer = new FileWriter(INCOMES_FILE)) {
            gson.toJson(incomes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
