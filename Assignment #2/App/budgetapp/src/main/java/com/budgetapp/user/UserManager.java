package com.budgetapp.user;
import com.google.gson.Gson;
import com.budgetapp.database.records;
import java.io.*;
import java.util.ArrayList;

/**
 * Using the Singleton design pattern for the UserManager
 */
public class UserManager {
    // The only instance of the UserManager object
    private static UserManager instance;

    private final Gson gson;
    private final ArrayList<records> records = new ArrayList<>();

    private static final String USERS_FILE = "Assignment #2\\App\\budgetapp\\src\\main\\java\\com\\budgetapp\\database\\database.json";
    
    // Private constructor
    private UserManager() {
        gson = new Gson();
        // Load all database rows into an array of record objects
        File file = new File(USERS_FILE);
        if (file.exists() && file.length() > 0) {
            try (Reader reader = new FileReader(file)) {
                records[] recordsArray = gson.fromJson(reader, records[].class);
                if (recordsArray != null) {
                    records.clear();
                    records.addAll(java.util.Arrays.asList(recordsArray));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveUsersToFile() {
        try (Writer writer = new FileWriter(USERS_FILE)) {
            gson.toJson(records, writer);
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
    public boolean insertRecord(records rec) {

        if (records.contains(rec)) {
            return false;
        }
        records.add(rec);
        saveUsersToFile();
        System.out.println("The records file has been updated ");
        return true;
    }

    public boolean deleteRecord(records rec) {

        if (records.contains(rec)) {
            records.remove(rec);
            saveUsersToFile();
            return true;
        }
        System.out.println("record not found");
        return false;
    }
    //! function to update a field in the record
    public boolean updateRecordField(String fieldName, Object oldValue, Object newValue) {
        for (records rec : records) {
            // Implement your logic to update a field in records
            // Example: if (rec.getField().equals(oldValue)) { rec.setField(newValue); ... }
        }
        System.out.println("record not found or update logic not implemented");
        return false;
    }
}
