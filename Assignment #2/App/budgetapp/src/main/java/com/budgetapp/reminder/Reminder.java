package com.budgetapp.reminder;


/**
 * Represents a reminder in the Budget App.
 *   @author Abdallah Mohamed
 *   @version 2.0
 *   @since 2025-05-10
 */
public class Reminder {

    private String UUID;
    private int id ;
    private String title;
    private String date;
    private String description;
    private String time;

    /**
     * Constructs a new Reminder with the specified attributes.
     *
     * @param UUID        the unique identifier of the user
     * @param  id
     * @param title       the title of the reminder
     * @param date        the date the reminder is scheduled for
     * @param description the description or notes about the reminder
     * @param time        the time the reminder is scheduled for
     */
    public Reminder(String UUID, int id  ,String title, String date, String description, String time) {
        this.UUID = UUID;
        this.title = title;
        this.id = id;
        this.date = date;
        this.description = description;
        this.time = time;
    }

    /** @return the UUID of the reminder */
    public String getUUID() {
        return UUID;
    }



    /** @return the title of the reminder */
    public String getTitle() {
        return title;
    }

    /** @param title the title to set */
    public void setTitle(String title) {
        this.title = title;
    }

    /** @return the date of the reminder */
    public String getDate() {
        return date;
    }

    /** @param date the date to set */
    public void setDate(String date) {
        this.date = date;
    }

    /** @return the description of the reminder */
    public String getDescription() {
        return description;
    }

    /** @param description the description to set */
    public void setDescription(String description) {
        this.description = description;
    }

    /** @return the time of the reminder */
    public String getTime() {
        return time;
    }

    /** @param time the time to set */
    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public String getSummary() {
        return  "id : " + this.id + " title : " + this.title + "Description: " + this.description +  " Time : " + this.time + " date : " + this.date;
    }
}
