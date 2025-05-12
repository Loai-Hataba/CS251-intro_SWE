package com.budgetapp.notification;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;
import java.util.ArrayList;
import java.util.List;

public class NotificationManager extends Observer {

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

    // Updates the notification based on an event
    public void update(String event) {
        System.out.println("Notification updated due to event: " + event);
        // Add update logic here
    }
}
