package com.budgetapp.user;

import com.budgetapp.notification.Notification;
import com.budgetapp.system.UI;

/**
 * Observer implementation for the current user.
 * This class handles notifications for the current active user.
 */
public class CurrentUserObserver extends Observer {
    private String userUUID;
    private UI ui;

    /**
     * Constructs a new CurrentUserObserver for a specific user.
     * 
     * @param userUUID The UUID of the user to observe
     * @param ui The UI instance to display notifications
     */
    public CurrentUserObserver(String userUUID, UI ui) {
        this.userUUID = userUUID;
        this.ui = ui;
    }

    /**
     * Gets the UUID of the user being observed.
     * 
     * @return The user's UUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * Receives a notification update and processes it if it's for this user.
     * 
     * @param notification The notification to process
     */
    @Override
    public void update(Notification notification) {
        // Only process notifications for this user
        if (notification.getUUID().equals(userUUID)) {
            // If there's a UI instance, display the message
            if (ui != null) {
                ui.showMessage("REMINDER: " + notification.getMessage(), notification.getDate() + " at " + notification.getTimestamp());
            } else {
                System.out.println("Notification for user " + userUUID + ": " + notification.getMessage());
            }
        }
    }
}
