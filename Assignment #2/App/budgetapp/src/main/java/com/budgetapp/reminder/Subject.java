package com.budgetapp.reminder;

import com.budgetapp.notification.Observer;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
