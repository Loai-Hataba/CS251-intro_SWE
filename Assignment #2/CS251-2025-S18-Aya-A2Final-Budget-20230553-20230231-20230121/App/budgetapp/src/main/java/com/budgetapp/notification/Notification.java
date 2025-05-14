package com.budgetapp.notification;

/**
 * Represents a notification in the Budget App system.
 *   @author Abdallah Mohamed
 *   @version 2.0
 *   @since 2025-05-10
 *
 */
public class Notification {
    private String UUID;
    private int id;
    private String message;
    private String date;
    private String timestamp;
    private boolean isRead;
    private boolean isSent;

    /**
     * Constructs a new Notification with the given properties.
     *
     * @param uuid      the unique identifier of the user
     * @param id        the numeric ID of the notification
     * @param message   the message content of the notification
     * @param date      the date the notification was created
     * @param timestamp the exact timestamp of the notification
     * @param isRead    whether the notification has been read
     * @param isSent    whether the notification has been sent
     */
    public Notification(String uuid, int id, String message, String date, String timestamp, boolean isRead, boolean isSent) {
        this.UUID = uuid;
        this.id = id;
        this.message = message;
        this.date = date;
        this.timestamp = timestamp;
        this.isRead = isRead;
        this.isSent = isSent;
    }

    /** @return the UUID of the user */
    public String getUUID() {
        return UUID;
    }

    /** @return the numeric ID of the notification */
    public int getId() {
        return id;
    }

    /** @return the message of the notification */
    public String getMessage() {
        return message;
    }

    /** @param message the message to set */
    public void setMessage(String message) {
        this.message = message;
    }

    /** @return the creation date of the notification */
    public String getDate() {
        return date;
    }

    /** @param date the date to set */
    public void setDate(String date) {
        this.date = date;
    }

    /** @return the timestamp of the notification */
    public String getTimestamp() {
        return timestamp;
    }

    /** @param timestamp the timestamp to set */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /** @return true if the notification has been read, otherwise false */
    public boolean isRead() {
        return isRead;
    }

    /** @param read true if the notification has been read */
    public void setRead(boolean read) {
        isRead = read;
    }

    /** @return true if the notification has been sent, otherwise false */
    public boolean isSent() {
        return isSent;
    }

    /** @param sent true if the notification has been sent */
    public void setSent(boolean sent) {
        isSent = sent;
    }
}
