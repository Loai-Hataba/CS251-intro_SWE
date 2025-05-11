package com.budgetapp.transaction;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncomeManager implements  ITransactionManager{

    // The Attributes :
    private IncomeManager instance ;
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
    public IncomeManager getInstance() {
        if (instance == null) {
            instance = new IncomeManager();
        }
        return instance;
    }

    // The interface functions :
    @Override
    public boolean add (String  title , Date date , String category , double amount , boolean isRecurring ){
        if (title == null || title == ""){
            System.out.println("Title is null or empty");
            return false;
        }
        if (date == null || date.after(new Date())){
            System.out.println("Date is null or empty");
            return false;
        }
        if (category == null || category == ""){
            System.out.println("Category is null or empty");
            return false;
        }
        if (amount < 0 || amount > Double.MAX_VALUE){
            System.out.println("Invalid amount");
            return false;
        }
        return true;
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
