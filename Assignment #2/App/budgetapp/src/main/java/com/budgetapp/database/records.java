package com.budgetapp.database;

import java.util.List;
import java.util.Objects;

public class records {
    public static class Income {
        public String source;
        public double amount;

        public Income() {}
        public Income(String source, double amount) {
            this.source = source;
            this.amount = amount;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Income income = (Income) o;
            return Double.compare(income.amount, amount) == 0 && Objects.equals(source, income.source);
        }
        @Override
        public int hashCode() {
            return Objects.hash(source, amount);
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
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Budget budget = (Budget) o;
            return Double.compare(budget.amount, amount) == 0 && Objects.equals(category, budget.category);
        }
        @Override
        public int hashCode() {
            return Objects.hash(category, amount);
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
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Reminder reminder = (Reminder) o;
            return Objects.equals(reminderName, reminder.reminderName) && Objects.equals(date, reminder.date) && Objects.equals(time, reminder.time);
        }
        @Override
        public int hashCode() {
            return Objects.hash(reminderName, date, time);
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
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Expense expense = (Expense) o;
            return Double.compare(expense.amount, amount) == 0 && Objects.equals(category, expense.category) && Objects.equals(date, expense.date);
        }
        @Override
        public int hashCode() {
            return Objects.hash(category, amount, date);
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

    public records() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        records that = (records) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, phoneNumber);
    }
}
