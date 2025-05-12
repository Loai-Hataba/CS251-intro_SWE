package com.budgetapp.transaction;

import java.util.ArrayList;
import java.util.List;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;

/**
 * Manages income transactions in the budget application. This class implements
 * the Singleton pattern and ITransactionManager interface.
 */
public class IncomeManager implements ITransactionManager {

    /**
     * The single instance of IncomeManager
     */
    private static IncomeManager instance;

    /**
     * List to store current income transactions
     */
    private List<Income> currentIncomes;

    /**
     * Private constructor to prevent direct instantiation. Initializes the
     * currentIncomes list.
     */
    private IncomeManager() {
        currentIncomes = new ArrayList<>();
    }

    /**
     * Returns the single instance of IncomeManager. Creates the instance if it
     * doesn't exist.
     *
     * @return The IncomeManager instance
     */
    public static IncomeManager getInstance() {
        if (instance == null) {
            instance = new IncomeManager();
        }
        return instance;
    }

    /**
     * Adds a new income transaction to the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param source The source/title of the income
     * @param amount The amount of money received
     * @param category The category of the income
     * @param date The date of the income
     * @param isRecurring Whether this is a recurring income
     * @return true if the income was successfully added, false otherwise
     */
    @Override
    public boolean add(String UUID, String source, double amount, String category, String date, boolean isRecurring) {
        Records userRecord = Methods.getUserRecord(UUID, false);
        if (userRecord == null) {
            return false;
        }

        currentIncomes = userRecord.income;
        int size = (currentIncomes == null) ? 1 : userRecord.income.size() + 1;

        Income income = new Income(UUID, size, source, amount, category, date, isRecurring);
        currentIncomes.add(income);

        return Methods.updateRecordField(UUID, "income", currentIncomes);
    }

    /**
     * Removes an income transaction from the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param id The ID of the income to remove
     * @return true if the income was successfully removed, false otherwise
     */
    @Override
    public boolean remove(String UUID, int id) {
        Records userRecord = Methods.getUserRecord(UUID, true);
        if (userRecord == null) {
            return false;
        }

        currentIncomes = userRecord.income;
        if (id > currentIncomes.size()) {
            System.out.println("The entered id is greater than the number of records in the income list");
            return false;
        }

        currentIncomes.removeIf(income -> income.getId() == id);

        for (int i = 0; i < currentIncomes.size(); i++) {
            currentIncomes.get(i).setId(i + 1);
        }

        return Methods.updateRecordField(UUID, "income", currentIncomes);
    }

    /**
     * Edits an existing income transaction in the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param id The ID of the income to edit
     * @param source The new source/title of the income
     * @param amount The new amount of money received
     * @param category The new category of the income
     * @param date The new date of the income
     * @param isRecurring The new recurring status of the income
     * @return true if the income was successfully edited, false otherwise
     */
    @Override
    public boolean edit(String UUID, int id, String source, double amount, String category, String date, boolean isRecurring) {
        Records userRecord = Methods.getUserRecord(UUID, true);
        if (userRecord == null) {
            return false;
        }

        currentIncomes = userRecord.income;
        if (id > currentIncomes.size()) {
            System.out.println("The entered id is greater than the number of records in the income list");
            return false;
        }

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

    /**
     * Retrieves a summary of all income transactions for a user.
     *
     * @param UUID The unique identifier of the user
     * @return List of income summaries as strings, null if user not found,
     * empty list if no incomes
     */
    @Override
    public List<String> summary(String UUID) {
        Records userRecord = Methods.getUserRecord(UUID, false);
        if (userRecord == null) {
            return null;
        }

        currentIncomes = userRecord.income;
        if (currentIncomes == null) {
            return new ArrayList<>();
        }

        List<String> summaries = new ArrayList<>();
        for (Income income : currentIncomes) {
            summaries.add(income.getSummary());
        }
        return summaries;
    }
}
