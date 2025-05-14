package com.budgetapp.reminder;

import java.util.ArrayList;
import java.util.List;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;
import com.budgetapp.notification.Notification;
import com.budgetapp.user.Observer;

/**
 * Manages reminders for users, providing functionality to add, edit,
 * delete, and summarize reminders. It also implements the Subject
 * interface to notify observers when a new reminder is added.
 */
public class ReminderManager implements Subject {

    private static ReminderManager instance;
    private List<Reminder> currentReminders;

    private List<Observer> observers = new ArrayList<>();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private ReminderManager() {
        currentReminders = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of ReminderManager.
     *
     * @return the single instance of ReminderManager
     */
    public static ReminderManager getInstance() {
        if (instance == null) {
            instance = new ReminderManager();
        }
        return instance;
    }

    /**
     * Adds a new reminder to the user's record and notifies observers.
     *
     * @param UUID        the unique identifier of the user
     * @param title       the title of the reminder
     * @param description the description or note for the reminder
     * @param date        the date the reminder is scheduled for
     * @param time        the time the reminder is scheduled for
     * @return true if the reminder was successfully added; false otherwise
     */
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

        Notification notification = new Notification(UUID, size, title + ": " + description, date, time, false, false);
        notifyObservers(notification);

        return Methods.updateRecordField(UUID, "reminder", currentReminders);
    }

    /**
     * Deletes a reminder from the user's record by its ID.
     *
     * @param UUID the unique identifier of the user
     * @param id   the ID of the reminder to delete
     * @return true if the reminder was successfully deleted; false otherwise
     */
    public boolean delete(String UUID, int id) {
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            return false;
        }

        currentReminders = userRecord.reminder;

        if (currentReminders == null || currentReminders.isEmpty()) {
            System.out.println("Error : Trying to edit an empty reminders list");
            return false;
        }

        if (id > currentReminders.size()) {
            System.out.println("Error : The entered id is greater than the number of reminders");
            return false;
        }

        for (Reminder reminder : currentReminders) {
            if (reminder.getId() == id) {
                currentReminders.remove(reminder);
                break;
            }
        }

        for (int i = 0; i < currentReminders.size(); i++) {
            currentReminders.get(i).setId(i + 1);
        }

        return Methods.updateRecordField(UUID, "reminder", currentReminders);
    }

    /**
     * Edits an existing reminder in the user's record.
     *
     * @param UUID        the unique identifier of the user
     * @param id          the ID of the reminder to edit
     * @param title       the new title of the reminder
     * @param description the new description of the reminder
     * @param date        the new date for the reminder
     * @param time        the new time for the reminder
     * @return true if the reminder was successfully edited; false otherwise
     */
    public boolean edit(String UUID, int id, String title, String description, String date, String time) {
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return false;
        }

        currentReminders = userRecord.reminder;

        if (currentReminders == null || currentReminders.isEmpty()) {
            System.out.println("Error : Trying to edit an empty reminders list");
            return false;
        }

        if (id > currentReminders.size()) {
            System.out.println("Error : The entered id is greater than the number of reminders");
            return false;
        }

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

    /**
     * Returns a list of summary strings for all reminders of the user.
     *
     * @param UUID the unique identifier of the user
     * @return a list of reminder summaries; empty if none exist
     */
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

    /**
     * Registers an observer to be notified of new reminders.
     *
     * @param observer the observer to register
     */
    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes a registered observer.
     *
     * @param observer the observer to remove
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers of a new reminder via a notification.
     *
     * @param notification the notification to send to all observers
     */
    @Override
    public void notifyObservers(Notification notification) {
        for (Observer observer : observers) {
            observer.update(notification);
        }
    }

}

























// package com.budgetapp.reminder;

// import java.util.ArrayList;
// import java.util.List;

// import com.budgetapp.database.Records;
// import com.budgetapp.methods.Methods;
// import com.budgetapp.notification.Notification;
// import com.budgetapp.user.Observer;

// public class ReminderManager implements Subject {

//     private static ReminderManager instance;
//     private List<Reminder> currentReminders;

//     private List<Observer> observers = new ArrayList<>();

//     private ReminderManager() {
//         currentReminders = new ArrayList<>();
//     }

//     public static ReminderManager getInstance() {
//         if (instance == null) {
//             instance = new ReminderManager();
//         }
//         return instance;
//     }

//     public boolean add(String UUID, String title, String description, String date, String time) {
//         Records userRecord = Methods.getRecordById(UUID);
//         if (userRecord == null) {
//             return false;
//         }

//         if (userRecord.reminder != null) {
//             currentReminders = userRecord.reminder;
//         } else {
//             currentReminders = new ArrayList<>();
//         }
//         int size = (currentReminders == null) ? 1 : currentReminders.size() + 1;

//         Reminder reminder = new Reminder(UUID, size, title, date, description, time);
//         currentReminders.add(reminder);
        
//         Notification notification = new Notification(UUID,size,title +": "+ description, date, time,false, false);
//         //public Notification(String uuid, int id, String message, Date date, Timestamp timestamp, boolean isRead, boolean isSent)
//         // Notify observers about the new reminder
//         notifyObservers(notification);

//         return Methods.updateRecordField(UUID, "reminder", currentReminders);
//     }
//     public boolean delete(String UUID, int id) {
//         // Get the user record
//         Records userRecord = Methods.getRecordById(UUID);
//         if (userRecord == null) {
//             return false;
//         }
//         currentReminders = userRecord.reminder;
//         // Validate the user record
//         if (currentReminders.isEmpty() || currentReminders == null) {
//             System.out.println("Error : Trying to edit an empty reminders list");
//         }
//         if (id > currentReminders.size()) {
//             System.out.println("Error : The entered id is greater than the number of incomes");
//             return false;
//         }

//         // The main logic of deleting :
//         for (Reminder reminder : currentReminders) {
//             if (reminder.getId() == id) {
//                 currentReminders.remove(reminder);
//                 break;
//             }
//         }
//         for (int i = 0; i < currentReminders.size(); i++) {
//             currentReminders.get(i).setId(i + 1);
//         }

//         // insert the new list into the user record
//         return Methods.updateRecordField(UUID, "reminder", currentReminders);
//     }

//     public boolean edit(String UUID, int id, String title, String description, String date, String time) {
//         // Get the user record
//         Records userRecord = Methods.getRecordById(UUID);
//         if (userRecord == null) {
//             System.out.println("User record not found for UUID: " + UUID);
//             return false;
//         }
//         currentReminders = userRecord.reminder;
//         // Validate the user record
//         if (currentReminders.isEmpty() || currentReminders == null) {
//             System.out.println("Error : Trying to edit an empty reminders list");
//         }
//         if (id > currentReminders.size()) {
//             System.out.println("Error : The entered id is greater than the number of incomes");
//             return false;
//         }

//         // The main logic of editing :
//         for (Reminder reminder : currentReminders) {
//             if (reminder.getId() == id) {
//                 reminder.setTitle(title);
//                 reminder.setDate(date);
//                 reminder.setDescription(description);
//                 reminder.setTime(time);
//                 break;
//             }
//         }
//         return Methods.updateRecordField(UUID, "reminder", currentReminders);
//     }

//     public List<String> summary(String UUID) {

//         Records userRecord = Methods.getRecordById(UUID);
//         if (userRecord == null) {
//             System.out.println("User record not found for UUID: " + UUID);
//             return null;
//         }
//         currentReminders = userRecord.reminder;
//         if (currentReminders == null) {
//             return new ArrayList<>();
//         }
//         List<String> summaries = new ArrayList<>();
//         for (Reminder reminder : currentReminders) {
//             summaries.add(reminder.getSummary());
//         }
//         return summaries;
//     }

//     @Override
//     public void registerObserver(Observer observer) {
//         if (!observers.contains(observer)) {
//             observers.add(observer);
//         }
//     }

//     @Override
//     public void removeObserver(Observer observer) {
//         observers.remove(observer);
//     }

//     @Override
//     public void notifyObservers(Notification notification) {
//         for (Observer observer : observers) {
//             observer.update(notification);
//         }
//     }

// }
