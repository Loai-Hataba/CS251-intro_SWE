package com.budgetapp.user;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.google.gson.Gson;

public class SessionManager {

    private static SessionManager instance;

    private final Gson gson;

    private static final String SESSIONS_FILE = "sessions.json";

    // the array that holds the sessions
    private ArrayList<Session> sessions;

    private SessionManager() {
        // set the array list to an empty list
        sessions = new ArrayList<>();
        gson = new Gson();
        // Create the file if it doesn't exist
        File file = new File(SESSIONS_FILE);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(sessions, writer); // Write empty array
                System.out.println("Created users.json");
            } catch (IOException e) {
            }
        }
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // private function to save the list of sessions in a json file
    private void saveSessionsToFile() {
        try (Writer writer = new FileWriter(SESSIONS_FILE)) {
            gson.toJson(sessions, writer);
        } catch (IOException e) {
        }
    }

    private boolean validateUser(String userId) {
        // First open the users database
        File file = new File("users.json");
        if (!file.exists()) {
            System.out.println("users.json not found.");
            return false;
        }
        // then iterates over all users to find if the user in the database or not

        try (Reader reader = new FileReader(file)) {
            User[] users = gson.fromJson(reader, User[].class);

            for (User user : users) {
                if (user.getUserId().equals(userId)) {
                    return true; // User ID found
                }
            }

        } catch (IOException e) {
        }

        return false; // User ID not found
    }

    //!
    public void createSession(String userId) {

        // validate the user id & return if user is invalid
        if (!validateUser(userId)) {
            System.out.println("Invalid user.");
            return;
        }
        // the user is valid
        // create the session id using uuid
        String sessionId = UUID.randomUUID().toString();

        Date startDate = new Date();  // current date and time

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.HOUR, 3);  // add 1 hour

        Date endDate = calendar.getTime();

        sessions.add(new Session(sessionId, userId, startDate, endDate));
        saveSessionsToFile();

        System.out.println("A session  has been created for : " + userId);

    }

    //!
    public void endOutDatedSessions() {
        for (Session session : sessions) {
            if (session.isActive()) {
                session.checkSessionIfExpired();
            }
        }
        System.out.println("All outDated sessions now are inactive.");
    }

}
