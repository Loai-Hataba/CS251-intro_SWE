/**
 *
 * @author Abdallah Mohamed
 * @version 2.0
 * @since 2025-05-10
 */
package com.budgetapp.transaction;

import java.util.Date;

public interface ITransaction {

    /**
     * Sets the amount of the transaction.
     *
     * @param amount the monetary value of the transaction
     */
    void setAmount(double amount);

    /**
     * Sets the title of the transaction.
     *
     * @param title  the title of the transaction
     */
    void setTitle(String title);

    /**
     * Sets the date of the transaction.
     *
     * @param date the date of the transaction
     */
    void setDate(Date date);

    /**
     * Marks whether the transaction is recurring.
     *
     * @param recurring {@code true} if the transaction is recurring, {@code false} otherwise
     */
    void markAsRecurring(boolean recurring);

    /**
     * Sets the category of the transaction (e.g., Food, Rent, Utilities).
     *
     * @param category the category of the transaction
     */
    void setCategory(String category);

    /**
     * Gets the id of the transaction.
     *
     * @return the transaction ID
     */
    int getId();

    /**
     * Gets the ID of the user who owns this transaction.
     *
     * @return the user ID as a {@code String}
     */
    String getUserId();

    /**
     * Gets the title or description of the transaction.
     *
     * @return the transaction title
     */
    String getTitle();

    /**
     * Gets the date on which the transaction occurred.
     *
     * @return the transaction date
     */
    Date getDate();

    /**
     * Checks whether the transaction is marked as recurring.
     *
     * @return {@code true} if recurring, {@code false} otherwise
     */
    boolean isRecurring();

    /**
     * Gets a summary description of the transaction.
     *
     * @return a string summary of the transaction
     */
    String getSummary();

    /**
     * Gets the amount of the transaction.
     *
     * @return the transaction amount
     */
    double getAmount();

    /**
     * Gets the category of the transaction.
     *
     * @return the transaction category
     */
    String getCategory();
}

