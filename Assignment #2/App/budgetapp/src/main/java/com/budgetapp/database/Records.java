package com.budgetapp.database;

import java.util.List;
import java.util.Objects;

import com.budgetapp.budget.Budget;
import com.budgetapp.reminder.Reminder;
import com.budgetapp.transaction.Expense;
import com.budgetapp.transaction.Income;
import com.budgetapp.user.User;

/**
 * Represents a database record containing user information and their financial
 * data. This class serves as a data transfer object between the application and
 * database.
 */
public class Records {

    /**
     * Unique identifier for the record
     */
    public String id;

    /**
     * Username of the account owner
     */
    public String username;

    /**
     * Hashed password of the account
     */
    public String password;

    /**
     * Email address of the user
     */
    public String email;

    /**
     * Phone number of the user
     */
    public String phoneNumber;

    /**
     * Country of residence
     */
    public String country;

    /**
     * List of income transactions
     */
    public List<Income> income;

    /**
     * List of budget entries
     */
    public List<Budget> budget;

    /**
     * List of reminders
     */
    public List<Reminder> reminder;

    /**
     * List of expense transactions
     */
    public List<Expense> expense;

    /**
     * Default constructor required for JSON deserialization.
     */
    public Records() {
    }

    /**
     * Creates a new record from a User object. Initializes basic user
     * information without financial data.
     *
     * @param user The User object containing account information
     */
    public Records(User user) {
        this.id = user.getUserId();
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.country = user.getCountry();
    }

    /**
     * Checks if two Records objects are equal based on their IDs.
     *
     * @param o Object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Records that = (Records) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Generates a hash code for the Records object.
     *
     * @return Hash code based on id, username, email, and phoneNumber
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, phoneNumber);
    }
}
