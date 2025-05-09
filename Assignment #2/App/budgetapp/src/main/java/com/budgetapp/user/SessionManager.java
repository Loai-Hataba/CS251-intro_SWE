package com.budgetapp.user;

public class SessionManager {
    private static SessionManager instance ;

    private SessionManager() {
    }
    public static SessionManager getInstance() {
        return instance;
    }
    public boolean createSession() {
        return false;
    }
    public void endOutDatedSession() {
    }
    public void checkSessionExpiry () {

    }

}
