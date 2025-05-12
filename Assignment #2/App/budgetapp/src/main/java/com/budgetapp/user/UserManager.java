package com.budgetapp.user;
import com.google.gson.Gson;
import com.budgetapp.database.Records;
import com.budgetapp.methods.GsonTool;
import com.budgetapp.methods.Methods;

// import java.io.*;
// import java.util.ArrayList;
import java.util.HashMap;
// import java.util.Iterator;
// import java.lang.reflect.Field;

/**
 * Using the Singleton design pattern for the UserManager
 */
public class UserManager {
    // The only instance of the UserManager object
    private static UserManager instance;

    private static final Gson gson = GsonTool.getGson();
    private final HashMap<String, Records> records;
    // private static final String RECORDS_FILE = "Assignment #2\\App\\budgetapp\\src\\main\\java\\com\\budgetapp\\database\\database.json";
    
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
