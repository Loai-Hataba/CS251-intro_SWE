package com.budgetapp.transaction;

import java.util.ArrayList;
import java.util.List;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;

public class IncomeManager implements ITransactionManager {

    private static IncomeManager instance;
    private List<Income> currentIncomes;

    private IncomeManager() {
        currentIncomes = new ArrayList<>();
    }

    public static IncomeManager getInstance() {
        if (instance == null) {
            instance = new IncomeManager();
        }
        return instance;
    }

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
