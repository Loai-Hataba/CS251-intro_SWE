package com.budgetapp.reminder;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;
import com.budgetapp.notification.Observer;
import com.budgetapp.reminder.Subject;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReminderManager implements Subject {
    private  static ReminderManager instance ;
    private List<Observer> observers = new ArrayList<>();
    private String msg;

    // The private constructor :
    private ReminderManager() {
    }
    public static ReminderManager getInstance() {
        if (instance == null) {
            instance = new ReminderManager();
        }
        return instance;
    }

    public boolean add(String UUID , String title , Date date, String description, Time time) {

        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        // Prepare a list to add
        List<Reminder> newReminders = new ArrayList<>();
        Reminder reminder = new Reminder(UUID, userRecord.reminder.size() + 1 ,title, date, description, time);
        newReminders.add(reminder);

        // If the user already has a reminder list, merge it
        if (userRecord.reminder != null) {
            newReminders.addAll(0, userRecord.reminder); // add existing reminders at the start
        }

        // Now update the field with the new list
        return Methods.updateRecordField(UUID, "reminder", newReminders);
    }
    
    public boolean delete(String UUID , int id  ) {
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        List<Reminder> currenReminders = userRecord.reminder;
        // Validate the user record
        if (currenReminders.isEmpty()) {
            System.out.println("reminder list for UUID: " + UUID + " is empty");
            return false;
        }
        if(id > currenReminders.size()){
            System.out.println("The entered id is greater than the number of records in the reminder list");
            return false;
        }

        // The main logic of deleting :
        for (Reminder reminder : currenReminders) {
            if (reminder.getId() == id) {
                currenReminders.remove(reminder);
                break;
            }
        }
        // insert the new list into the user record
        return Methods.updateRecordField(UUID, "reminder", currenReminders);
    }
    
    
    public boolean update(String UUID , int id  ,String title , Date date, String description, Time time) {
        // Get the user record
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }
        List<Reminder> currentReminders = userRecord.reminder;
        // Validate the user record
        if (currentReminders.isEmpty()) {
            System.out.println("reminder list for UUID: " + UUID + " is empty");
            return false;
        }
        if(id > currentReminders.size()){
            System.out.println("The entered id is greater than the number of records in the reminder list");
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
        List<Reminder> currentReminders = userRecord.reminder;
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
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(msg); // Send the current message
        }
    }

}
