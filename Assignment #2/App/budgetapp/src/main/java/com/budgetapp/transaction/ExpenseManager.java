package com.budgetapp.transaction;

import java.util.ArrayList;
import java.util.List;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;

public class ExpenseManager implements ITransactionManager {

    private static ExpenseManager instance;

    private List<Expense> currentExpenses;

    private ExpenseManager() {
        currentExpenses = new ArrayList<>();
    }

    public static ExpenseManager getInstance() {
        if (instance == null) {
            instance = new ExpenseManager();
        }
        return instance;
    }

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
