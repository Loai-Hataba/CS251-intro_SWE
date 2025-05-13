package com.budgetapp.reminder;

import com.budgetapp.notification.Notification;
import com.budgetapp.user.Observer;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Notification notification);
}
