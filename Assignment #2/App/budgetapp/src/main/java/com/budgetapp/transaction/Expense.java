package com.budgetapp.transaction;

/**
 * Represents an expense transaction in the BudgetApp. Implements the
 * {@link ITransaction} interface and defines properties specific to expense..
 *
 * @author Abdallah Mohamed
 * @version 2.0
 * @since 2025-05-10
 */
public class Expense implements ITransaction {

    private int id;
    private String userId;
    private String title;
    private double amount;
    private String category;
    private String date;
    private boolean isRecurring;

    /**
     * Constructs a new {@code expense} object.
     *
     * @param id the ID of the expense
     * @param userId the user ID who owns the transaction
     * @param title the expense title
     * @param amount the amount of money received
     * @param category the category of expense
     * @param date the date of the transaction
     * @param isRecurring {@code true} if the expense is recurring
     */
    // The Constructor :
    Expense(String userId, int id, String title, double amount, String category, String date, boolean isRecurring) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.title = title;
        this.userId = userId;
        this.isRecurring = isRecurring;
    }

    /**
     * Sets the amount of expense.
     *
     * @param amount the new amount
     */
    @Override
    public void setAmount(double amount) {
        if (amount < 0 || amount > Double.MAX_VALUE) {
            System.out.println("Invalid amount");
            return;
        }
        this.amount = amount;
    }

    /**
     * Sets the expense source/title.
     *
     * @param title the new title
     */
    @Override
    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            System.out.println("Invalid title");
            return;
        }
        this.title = title;
    }

    /**
     * Sets the transaction date.
     *
     * @param date the new date
     */
    @Override
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Marks the expense as recurring.
     *
     * @param recurring {@code true} if recurring
     */
    @Override
    public void markAsRecurring(boolean recurring) {
        this.isRecurring = recurring;
    }

    /**
     * Sets the expense category.
     *
     * @param category the new category
     */
    @Override
    public void setCategory(String category) {
        if (category == null || category.isEmpty()) {
            System.out.println("Invalid category");
            return;
        }
        this.category = category;
    }

    /**
     * Returns the ID of the expense.
     *
     * @return the expense ID
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Returns the user ID associated with the expense.
     *
     * @return the user ID
     */
    @Override
    public String getUserId() {
        return this.userId;
    }

    /**
     * Returns the title of the expense.
     *
     * @return the expense title
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the date of the expense.
     *
     * @return the expense date
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Returns the amount of the expense.
     *
     * @return the expense amount
     */
    @Override
    public double getAmount() {
        return this.amount;
    }

    /**
     * Returns the category of the expense.
     *
     * @return the expnese category
     */
    @Override
    public String getCategory() {
        return this.category;
    }

    /**
     * Checks if the expense is recurring.
     *
     * @return {@code true} if recurring, {@code false} otherwise
     */
    @Override
    public boolean isRecurring() {
        return this.isRecurring;
    }

    /**
     * Returns a string summary of the expense transaction.
     *
     * @return a summary of the expense
     */
    @Override
    public String getSummary() {
        return " | id : " + this.id + " | source : "
                + this.title + " | amount : " + this.amount + " | date : " + this.date + " | Category : "
                + this.category + " | Recurring : " + this.isRecurring + " |";
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

}
