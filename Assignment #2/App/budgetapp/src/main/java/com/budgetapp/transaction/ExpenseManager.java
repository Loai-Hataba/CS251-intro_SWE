package com.budgetapp.transaction;

import java.util.ArrayList;
import java.util.List;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;

/**
 * Manages expense transactions in the budget application. This class implements
 * the Singleton pattern and ITransactionManager interface.
 */
public class ExpenseManager implements ITransactionManager {

    /**
     * The single instance of ExpenseManager
     */
    private static ExpenseManager instance;

    /**
     * List to store current expense transactions
     */
    private List<Expense> currentExpenses;

    /**
     * Private constructor to prevent direct instantiation. Initializes the
     * currentExpenses list.
     */
    private ExpenseManager() {
        currentExpenses = new ArrayList<>();
    }

    /**
     * Returns the single instance of ExpenseManager. Creates the instance if it
     * doesn't exist.
     *
     * @return The ExpenseManager instance
     */
    public static ExpenseManager getInstance() {
        if (instance == null) {
            instance = new ExpenseManager();
        }
        return instance;
    }

    /**
     * Adds a new expense transaction to the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param source The source/title of the expense
     * @param amount The amount of money spent
     * @param category The category of the expense
     * @param date The date of the expense
     * @param isRecurring Whether this is a recurring expense
     * @return true if the expense was successfully added, false otherwise
     */
    @Override
    public boolean add(String UUID, String source, double amount, String category, String date, boolean isRecurring) {
        Records userRecord = Methods.getUserRecord(UUID, false);
        if (userRecord == null) {
            return false;
        }
        currentExpenses = userRecord.expense;
        int size = (currentExpenses == null) ? 1 : userRecord.expense.size() + 1;
        Expense expense = new Expense(UUID, size, source, amount, category, date, isRecurring);
        currentExpenses.add(expense);
        return Methods.updateRecordField(UUID, "expense", currentExpenses);
    }

    /**
     * Removes an expense transaction from the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param id The ID of the expense to remove
     * @return true if the expense was successfully removed, false otherwise
     */
    @Override
    public boolean remove(String UUID, int id) {
        Records userRecord = Methods.getUserRecord(UUID, true);
        if (userRecord == null) {
            return false;
        }

        currentExpenses = userRecord.expense;
        if (id > currentExpenses.size()) {
            System.out.println("The entered id is greater than the number of records in the expense list");
            return false;
        }

        currentExpenses.removeIf(expense -> expense.getId() == id);

        for (int i = 0; i < currentExpenses.size(); i++) {
            currentExpenses.get(i).setId(i + 1);
        }

        return Methods.updateRecordField(UUID, "expense", currentExpenses);
    }

    /**
     * Edits an existing expense transaction in the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param id The ID of the expense to edit
     * @param source The new source/title of the expense
     * @param amount The new amount of money spent
     * @param category The new category of the expense
     * @param date The new date of the expense
     * @param isRecurring The new recurring status of the expense
     * @return true if the expense was successfully edited, false otherwise
     */
    @Override
    public boolean edit(String UUID, int id, String source, double amount, String category, String date, boolean isRecurring) {
        Records userRecord = Methods.getUserRecord(UUID, true);
        if (userRecord == null) {
            return false;
        }

        currentExpenses = userRecord.expense;
        if (id > currentExpenses.size()) {
            System.out.println("The entered id is greater than the number of records in the expense list");
            return false;
        }

        for (Expense expense : currentExpenses) {
            if (expense.getId() == id) {
                expense.setTitle(source);
                expense.setAmount(amount);
                expense.setCategory(category);
                expense.setDate(date);
                expense.markAsRecurring(isRecurring);
                break;
            }
        }

        return Methods.updateRecordField(UUID, "expense", currentExpenses);
    }

    /**
     * Retrieves a summary of all expense transactions for a user.
     *
     * @param UUID The unique identifier of the user
     * @return List of expense summaries as strings, null if user not found,
     * empty list if no expenses
     */
    @Override
    public List<String> summary(String UUID) {
        Records userRecord = Methods.getUserRecord(UUID, false);
        if (userRecord == null) {
            return null;
        }

        currentExpenses = userRecord.expense;
        if (currentExpenses == null) {
            return new ArrayList<>();
        }

        List<String> summaries = new ArrayList<>();
        for (Expense expense : currentExpenses) {
            summaries.add(expense.getSummary());
        }
        return summaries;
    }
}
