package com.budgetapp.user;
import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;

/**
 * Using the Singleton design pattern for the UserManager
 */
public class UserManager {
    // The only instance of the UserManager object
    private static UserManager instance;

    private final Gson gson;
    private final ArrayList<User> users;

    private static final String USERS_FILE = "users.json";

    // Private constructor
    private UserManager() {
        gson = new Gson();
        users = new ArrayList<>();

        // Create the file if it doesn't exist
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(users, writer); // Write empty array
                System.out.println("Created users.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveUsersToFile() {
        try (Writer writer = new FileWriter(USERS_FILE)) {
            gson.toJson(users, writer);
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

    // Insert user into memory list (and you can extend this to save to file)
    public boolean insertUser(User user) {

        if (users.contains(user)) {
            return false;
        }
        users.add(user);
        saveUsersToFile();
        System.out.println("The user file has been updated ");
        return true;
    }

    public boolean deleteUser(User user) {

        if (users.contains(user)) {
            users.remove(user);
            saveUsersToFile();
            return true;
        }
        System.out.println("user not found");
        return false;
    }
    //! function to update the user password
    public boolean updateUserPassword(String username, String newPassword) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                // we have found the correct user
                user.setPassword(newPassword);
                saveUsersToFile();
                return true ;
            }
        }
        System.out.println("user not found");
        return false;
    }
}
