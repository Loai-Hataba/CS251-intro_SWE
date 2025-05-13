package com.budgetapp.reminder;

import java.util.ArrayList;
import java.util.List;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;
import com.budgetapp.notification.Notification;
import com.budgetapp.user.Observer;

public class ReminderManager implements Subject {

    private static ReminderManager instance;
    private List<Reminder> currentReminders;

    private List<Observer> observers = new ArrayList<>();

    private ReminderManager() {
        currentReminders = new ArrayList<>();
    }

    public static ReminderManager getInstance() {
        if (instance == null) {
            instance = new ReminderManager();
        }
        return instance;
    }

    public boolean add(String UUID, String title, String description, String date, String time) {
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            return false;
        }

        if (userRecord.reminder != null) {
            currentReminders = userRecord.reminder;
        } else {
            currentReminders = new ArrayList<>();
        }
        int size = (currentReminders == null) ? 1 : currentReminders.size() + 1;

        Reminder reminder = new Reminder(UUID, size, title, date, description, time);
        currentReminders.add(reminder);
        
        Notification notification = new Notification(UUID,size,title +": "+ description, date, time,false, false);
        //public Notification(String uuid, int id, String message, Date date, Timestamp timestamp, boolean isRead, boolean isSent)
        // Notify observers about the new reminder
        notifyObservers(notification);

        return Methods.updateRecordField(UUID, "reminder", currentReminders);
    }
    public boolean delete(String UUID, int id) {
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            return false;
        }
        currentReminders = userRecord.reminder;
        // Validate the user record
        if (currentReminders.isEmpty() || currentReminders == null) {
            System.out.println("Error : Trying to edit an empty reminders list");
        }
        if (id > currentReminders.size()) {
            System.out.println("Error : The entered id is greater than the number of incomes");
            return false;
        }

        // The main logic of deleting :
        for (Reminder reminder : currentReminders) {
            if (reminder.getId() == id) {
                currentReminders.remove(reminder);
                break;
            }
        }
        for (int i = 0; i < currentReminders.size(); i++) {
            currentReminders.get(i).setId(i + 1);
        }

        // insert the new list into the user record
        return Methods.updateRecordField(UUID, "reminder", currentReminders);
    }

    public boolean edit(String UUID, int id, String title, String description, String date, String time) {
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        currentReminders = userRecord.reminder;
        // Validate the user record
        if (currentReminders.isEmpty() || currentReminders == null) {
            System.out.println("Error : Trying to edit an empty reminders list");
        }
        if (id > currentReminders.size()) {
            System.out.println("Error : The entered id is greater than the number of incomes");
            return false;
        }

        // The main logic of editing :
        for (Reminder reminder : currentReminders) {
            if (reminder.getId() == id) {
                reminder.setTitle(title);
                reminder.setDate(date);
                reminder.setDescription(description);
                reminder.setTime(time);
                break;
            }
        }
        return Methods.updateRecordField(UUID, "reminder", currentReminders);
    }

    public List<String> summary(String UUID) {

        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return null;
        }
        currentReminders = userRecord.reminder;
        if (currentReminders == null) {
            return new ArrayList<>();
        }
        List<String> summaries = new ArrayList<>();
        for (Reminder reminder : currentReminders) {
            summaries.add(reminder.getSummary());
        }
        return summaries;
    }

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Notification notification) {
        for (Observer observer : observers) {
            observer.update(notification);
        }
    }

}
