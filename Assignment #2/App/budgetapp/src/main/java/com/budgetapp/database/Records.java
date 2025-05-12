package com.budgetapp.database;

import java.util.List;
import java.util.Objects;
import com.budgetapp.user.User;
import com.budgetapp.transaction.*;
import com.budgetapp.reminder.Reminder ;
import com.budgetapp.budget.Budget;

public class Records {

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
