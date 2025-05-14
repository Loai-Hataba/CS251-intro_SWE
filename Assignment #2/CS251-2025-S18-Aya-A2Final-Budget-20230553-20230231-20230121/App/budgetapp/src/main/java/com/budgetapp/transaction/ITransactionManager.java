package com.budgetapp.transaction;

import java.util.List;

/**
 * Interface defining the contract for managing financial transactions.
 * Implements basic CRUD operations and summary retrieval for transactions.
 */
public interface ITransactionManager {

    /**
     * Adds a new transaction to the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param source The source/title of the transaction
     * @param amount The amount of money involved
     * @param category The category of the transaction
     * @param date The date of the transaction
     * @param isRecurring Whether this is a recurring transaction
     * @return true if the transaction was successfully added, false otherwise
     */
    boolean add(String UUID, String source, double amount, String category, String date, boolean isRecurring);

    /**
     * Removes a transaction from the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param id The ID of the transaction to remove
     * @return true if the transaction was successfully removed, false otherwise
     */
    boolean remove(String UUID, int id);

    /**
     * Edits an existing transaction in the user's records.
     *
     * @param UUID The unique identifier of the user
     * @param id The ID of the transaction to edit
     * @param source The new source/title of the transaction
     * @param amount The new amount of money
     * @param category The new category of the transaction
     * @param date The new date of the transaction
     * @param isRecurring The new recurring status of the transaction
     * @return true if the transaction was successfully edited, false otherwise
     */
    boolean edit(String UUID, int id, String source, double amount, String category, String date, boolean isRecurring);

    /**
     * Retrieves a summary of all transactions for a user.
     *
     * @param UUID The unique identifier of the user
     * @return List of transaction summaries as strings
     */
    List<String> summary(String UUID);
}
