package com.budgetapp.notification;

public class NotificationManager {

    // Singleton instance
    private static NotificationManager instance;

    // Message to be used in notification
    private String msg;

    // Private constructor to prevent instantiation
    private NotificationManager() {
    }

    // Singleton accessor
    public static NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

    // Creates a notification with the given message
    public boolean createNotification(String message) {
        this.msg = message;
        System.out.println("Notification created: " + msg);
        return true;
    }
}
