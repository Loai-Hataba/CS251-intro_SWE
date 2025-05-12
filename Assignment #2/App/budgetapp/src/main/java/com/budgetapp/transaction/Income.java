package com.budgetapp.transaction;


/**
 * Represents an income transaction in the BudgetApp.
 * Implements the {@link ITransaction} interface and defines properties specific to income.
 *
 * @author
 * Abdallah Mohamed
 * @version 2.0
 * @since 2025-05-10
 */
public class Income implements ITransaction {

    private int id;
    private String userId;
    private String source;
    private String date;
    private double amount;
    private boolean isRecurring;
    private String category;

    /**
     * Constructs a new {@code Income} object.
     *
     * @param id          the ID of the income
     * @param userId      the user ID who owns the transaction
     * @param source      the income title/source
     * @param amount      the amount of money received
     * @param category    the category of income
     * @param date        the date of the transaction
     * @param isRecurring {@code true} if the income is recurring

     */
    Income(String userId, int id ,String source,double amount,String category,String date ,  boolean isRecurring) {
        this.id = id;
        this.source = source;
        this.date = date;
        this.amount = amount;
        this.isRecurring = isRecurring;
        this.category = category;
        this.userId = userId;
    }

    /**
     * Sets the amount of income.
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
     * Sets the income source/title.
     *
     * @param title the new title
     */
    @Override
    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            System.out.println("Invalid title");
            return;
        }
        this.source = title;
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
     * Marks the income as recurring.
     *
     * @param recurring {@code true} if recurring
     */
    @Override
    public void markAsRecurring(boolean recurring) {
        this.isRecurring = recurring;
    }

    /**
     * Sets the income category.
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
     * Returns the ID of the income.
     *
     * @return the income ID
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Returns the user ID associated with the income.
     *
     * @return the user ID
     */
    @Override
    public String getUserId() {
        return this.userId;
    }

    /**
     * Returns the title/source of the income.
     *
     * @return the income title
     */
    @Override
    public String getTitle() {
        return this.source;
    }

    /**
     * Returns the date of the income.
     *
     * @return the income date
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Returns the amount of the income.
     *
     * @return the income amount
     */
    @Override
    public double getAmount() {
        return this.amount;
    }

    /**
     * Returns the category of the income.
     *
     * @return the income category
     */
    @Override
    public String getCategory() {
        return this.category;
    }
    /**
     * Checks if the income is recurring.
     *
     * @return {@code true} if recurring, {@code false} otherwise
     */
    @Override
    public boolean isRecurring() {
        return this.isRecurring;
    }

    /**
     * Returns a string summary of the income transaction.
     *
     * @return a summary of the income
     */
    @Override
    public String getSummary() {
        return "UUID : " + this.userId + "id : " + this.id + " source : " + this.source + " amount : " + this.amount + " date : " + this.date;
    }


}
