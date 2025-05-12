package com.budgetapp.budget;

import java.util.ArrayList;
import java.util.List;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;

/**
 * Manages budget entries in the budget application. This class implements the
 * Singleton pattern for budget management.
 */
public class BudgetManager {

    /**
     * The single instance of BudgetManager
     */
    private static BudgetManager instance;

    /**
     * List to store current budget entries
     */
    private List<Budget> currentBudgets;

    /**
     * Private constructor to prevent direct instantiation. Initializes the
     * currentBudgets list.
     */
    private BudgetManager() {
        currentBudgets = new ArrayList<>();
    }

    /**
     * Returns the single instance of BudgetManager. Creates the instance if it
     * doesn't exist.
     *
     * @return The BudgetManager instance
     */
    public static BudgetManager getInstance() {
        if (instance == null) {
            instance = new BudgetManager();
        }
        return instance;
    }

    /**
     * Adds a new budget entry to the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param title The title of the budget
     * @param amount The budgeted amount
     * @param category The category of the budget
     * @param startDate The start date of the budget period
     * @param endDate The end date of the budget period
     * @return true if the budget was successfully added, false otherwise
     */
    public boolean add(String UUID, String title, double amount, String category, String startDate, String endDate) {
        Records userRecord = Methods.getUserRecord(UUID, false);
        if (userRecord == null) {
            return false;
        }
        if (userRecord.budget != null){
            currentBudgets = userRecord.budget;
        }else {
            currentBudgets = new ArrayList<>();
        }
        int size = (currentBudgets == null) ? 1 : currentBudgets.size() + 1;
        Budget budget = new Budget(UUID, size, title, startDate, endDate, amount, category);
        currentBudgets.add(budget);
        return Methods.updateRecordField(UUID, "budget", currentBudgets);
    }

    /**
     * Removes a budget entry from the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param id The ID of the budget to remove
     * @return true if the budget was successfully removed, false otherwise
     */
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

    /**
     * Edits an existing budget entry in the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param id The ID of the budget to edit
     * @param title The new title of the budget
     * @param amount The new budgeted amount
     * @param category The new category of the budget
     * @param startDate The new start date of the budget period
     * @param endDate The new end date of the budget period
     * @return true if the budget was successfully edited, false otherwise
     */
    public boolean edit(String UUID, int id, String title, double amount, String category, String startDate, String endDate) {
        Records userRecord = Methods.getUserRecord(UUID, true);
        if (userRecord == null) {
            return false;
        }
        currentBudgets = userRecord.budget;
        if (id > currentBudgets.size()) {
            System.out.println("The entered id is greater than the number of records in the Budget list");
            return false;
        }

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

    /**
     * Retrieves a summary of all budget entries for a user.
     *
     * @param UUID The unique identifier of the user
     * @return List of budget summaries as strings, null if user not found,
     * empty list if no budgets
     */
    public List<String> summary(String UUID) {
        Records userRecord = Methods.getUserRecord(UUID, false);
        if (userRecord == null) {
            return null;
        }
        currentBudgets = userRecord.budget;
        if (currentBudgets == null) {
            return new ArrayList<>();
        }
        List<String> summaries = new ArrayList<>();
        for (Budget Budget : currentBudgets) {
            summaries.add(Budget.getSummary());
        }
        return summaries;
    }
}
