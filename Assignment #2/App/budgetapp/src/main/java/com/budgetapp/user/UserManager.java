package com.budgetapp.user;
import com.google.gson.Gson;
import com.budgetapp.database.Records;
import com.budgetapp.methods.GsonTool;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Field;

/**
 * Using the Singleton design pattern for the UserManager
 */
public class UserManager {
    // The only instance of the UserManager object
    private static UserManager instance;

    private final Gson gson = GsonTool.getGson();
    private final HashMap<String, Records> records = new HashMap<>();

    private static final String USERS_FILE = "Assignment #2\\App\\budgetapp\\src\\main\\java\\com\\budgetapp\\database\\database.json";
    
    // Private constructor
    private UserManager() {
        // Load all database rows into an array of record objects
        File file = new File(USERS_FILE);
        if (file.exists() && file.length() > 0) {
            try (Reader reader = new FileReader(file)) {
                Records[] recordsArray = gson.fromJson(reader, Records[].class);
                if (recordsArray != null) {
                    records.clear();
                    for (Records rec : recordsArray) {
                        if (rec.id != null) {
                            records.put(rec.id, rec);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveUsersToFile() {
        try (Writer writer = new FileWriter(USERS_FILE)) {
            gson.toJson(records.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Singleton accessor method
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // Insert record into memory list (and you can extend this to save to file)
    public boolean insertRecord(Records rec) {
        if (rec.id == null || records.containsKey(rec.id)) {
            return false;
        }
        records.put(rec.id, rec);
        saveUsersToFile();
        System.out.println("The records file has been updated ");
        return true;
    }

    public boolean deleteRecord(String id) {
        if (records.containsKey(id)) {
            records.remove(id);
            saveUsersToFile();
            return true;
        }
        System.out.println("record not found");
        return false;
    }

    public Records getRecordById(String id) {
        return records.get(id);
    }

    //! function to update a field in the record
    public boolean updateRecordField(String id, String fieldName, Object newValue) {
        Records rec = records.get(id);
        if (rec == null) {
            System.out.println("record not found");
            return false;
        }
        try {
            Field field = rec.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(rec, newValue);
            saveUsersToFile();
            System.out.println("Field '" + fieldName + "' updated for record with id: " + id);
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Failed to update field: " + fieldName);
            e.printStackTrace();
            return false;
        }
    }
}
