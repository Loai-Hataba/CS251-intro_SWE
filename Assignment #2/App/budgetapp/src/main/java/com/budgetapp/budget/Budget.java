package com.budgetapp.budget;


/**
 * Represents a budget entry in the Budget App.
 *   @author Abdallah Mohamed
 *   @version 2.0
 *   @since 2025-05-10
 */
public class Budget {

    private String UUID;
    private int id;
    private String title;
    private String startDate;
    private String endDate;
    private double amount;
    private String category;

    /**
     * Constructs a new Budget object with the given attributes.
     *
     * @param UUID      the unique identifier of the user
     * @param id        the numeric ID of the budget
     * @param title     the title of the budget
     * @param startDate the start date of the budget
     * @param endDate   the end date of the budget
     * @param amount    the total amount allocated to the budget
     * @param category  the category of the budget (e.g., Food, Transport)
     */
    public Budget(String UUID, int id, String title, String startDate, String endDate, double amount, String category) {
        this.UUID = UUID;
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.category = category;
    }

    /** @return the UUID of the user */
    public String getUUID() {
        return UUID;
    }


    /** @return the ID of the budget */
    public int getId() {
        return id;
    }

    /** @param id the ID to set */
    public void setId(int id) {
        this.id = id;
    }

    /** @return the title of the budget */
    public String getTitle() {
        return title;
    }

    /** @param title the title to set */
    public void setTitle(String title) {
        this.title = title;
    }

    /** @return the start date of the budget */
    public String getStartDate() {
        return startDate;
    }

    /** @param startDate the start date to set */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /** @return the end date of the budget */
    public String getEndDate() {
        return endDate;
    }

    /** @param endDate the end date to set */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /** @return the total budget amount */
    public double getAmount() {
        return amount;
    }

    /** @param amount the amount to set */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /** @return the budget category */
    public String getCategory() {
        return category;
    }

    /** @param category the category to set */
    public void setCategory(String category) {
        this.category = category;
    }
    public String getSummary() {
        return  "id : " + this.id + " source : " + this.title + " amount : " + this.amount + "begin date : " + this.startDate + "End Date: " + this.endDate;
    }
}
