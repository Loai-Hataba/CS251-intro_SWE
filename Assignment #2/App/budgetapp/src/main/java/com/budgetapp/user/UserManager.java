package com.budgetapp.user;

import java.util.HashMap;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;

/**
 * Using the Singleton design pattern for the UserManager
 */
public class UserManager {

    // The only instance of the UserManager object
    private static UserManager instance;

    private final HashMap<String, Records> records;

    // Private constructor
    private UserManager() {
        records = Methods.loadRecords();
    }

    public boolean checkUserExists(String userName) {
        for (Records rec : records.values()) {
            if (rec.username != null && rec.username.equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public String checkUserCredentials(String userName, String password) {
        password = Methods.hashPassword(password);
        for (Records rec : records.values()) {
            if ((rec.username != null && rec.password != null) && (rec.username.equals(userName) && rec.password.equals(password))) {
                return rec.id;
            }
        }
        return "";
    }

    // Singleton accessor method
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

}
