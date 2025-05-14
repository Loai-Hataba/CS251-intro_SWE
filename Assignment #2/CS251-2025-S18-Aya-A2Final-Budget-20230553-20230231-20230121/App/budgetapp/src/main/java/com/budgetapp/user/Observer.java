package com.budgetapp.user;

import com.budgetapp.notification.Notification;

public abstract class Observer {

    public void update(Notification notification) {
        // Default implementation (can be overridden)
    }
}