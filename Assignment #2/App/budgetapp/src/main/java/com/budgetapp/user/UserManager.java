package com.budgetapp.user;
import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;

import java.util.HashMap;

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

    public boolean checkUserExists(String userName){
        for (Records rec : records.values()) {
            if (rec.username != null && rec.username.equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public String checkUserCredentials(String userName, String password){
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
