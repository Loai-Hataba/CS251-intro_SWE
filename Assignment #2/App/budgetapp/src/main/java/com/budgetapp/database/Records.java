package com.budgetapp.database;

import java.util.List;
import java.util.Objects;
import com.budgetapp.user.User;

public class Records {
    public static class Income {
        public String source;
        public double amount;

        public Income() {}
        public Income(String source, double amount) {
            this.source = source;
            this.amount = amount;
        }
    }

    public static class Budget {
        public String category;
        public double amount;

        public Budget() {}
        public Budget(String category, double amount) {
            this.category = category;
            this.amount = amount;
        }
    }

    public static class Reminder {
        public String reminderName;
        public String date;
        public String time;

        public Reminder() {}
        public Reminder(String reminderName, String date, String time) {
            this.reminderName = reminderName;
            this.date = date;
            this.time = time;
        }
    }

    public static class Expense {
        public String category;
        public double amount;
        public String date;

        public Expense() {}
        public Expense(String category, double amount, String date) {
            this.category = category;
            this.amount = amount;
            this.date = date;
        }
    }

    public String id;
    public String username;
    public String password;
    public String email;
    public String phoneNumber;
    public String country;
    public List<Income> income;
    public List<Budget> budget;
    public List<Reminder> reminder;
    public List<Expense> expense;

    public Records() {}

    public Records(User user) {
        this.id = user.getUserId();
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.country = user.getCountry();
        // The rest (income, budget, reminder, expense) can be set later
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Records that = (Records) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, phoneNumber);
    }
}
