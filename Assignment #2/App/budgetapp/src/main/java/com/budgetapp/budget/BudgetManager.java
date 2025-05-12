package com.budgetapp.budget;

import java.util.ArrayList;
import java.util.List;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;

public class BudgetManager {

    private static BudgetManager instance;
    private List<Budget> currentBudgets;

    private BudgetManager() {
        currentBudgets = new ArrayList<>();
    }

    public static BudgetManager getInstance() {
        if (instance == null) {
            instance = new BudgetManager();
        }
        return instance;
    }

    public boolean add(String UUID, String title, double amount, String category, String startDate, String endDate) {
        Records userRecord = Methods.getUserRecord(UUID, false);
        if (userRecord == null) {
            return false;
        }
        currentBudgets = userRecord.budget;
        int size = (userRecord.budget == null) ? 1 : userRecord.budget.size() + 1;
        Budget budget = new Budget(UUID, size, title, startDate, endDate, amount, category);
        currentBudgets.add(budget);
        return Methods.updateRecordField(UUID, "budget", currentBudgets);
    }

    public boolean remove(String UUID, int id) {
        Records userRecord = Methods.getUserRecord(UUID, true);
        if (userRecord == null) {
            return false;
        }

        currentBudgets = userRecord.budget;
        if (id > currentBudgets.size()) {
            System.out.println("The entered id is greater than the number of records in the Budget list");
            return false;
        }

        currentBudgets.removeIf(budget -> budget.getId() == id);

        // Update remaining IDs
        for (int i = 0; i < currentBudgets.size(); i++) {
            currentBudgets.get(i).setId(i + 1);
        }

        return Methods.updateRecordField(UUID, "budget", currentBudgets);
    }

    public boolean edit(String UUID, int id, String title, double amount, String category, String startDate, String endDate) {
        // Get the user record
        Records userRecord = Methods.getUserRecord(UUID, true);
        if (userRecord == null) {
            return false;
        }
        currentBudgets = userRecord.budget;
        if (id > currentBudgets.size()) {
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

    public List<String> summary(String UUID) {
        Records userRecord = Methods.getUserRecord(UUID, false);
        if (userRecord == null) {
            return null;
        }
        currentBudgets = userRecord.budget;
        if (currentBudgets == null) {
            List<String> emptyList = new ArrayList<>();
            return emptyList;
        }
        List<String> summaries = new ArrayList<>();
        for (Budget Budget : currentBudgets) {
            summaries.add(Budget.getSummary());
        }
        return summaries;
    }
}
