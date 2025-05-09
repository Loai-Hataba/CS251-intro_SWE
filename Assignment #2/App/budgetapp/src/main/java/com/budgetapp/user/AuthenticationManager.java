package com.budgetapp.user;

public class AuthenticationManager {

    private static AuthenticationManager instance ;

    private AuthenticationManager() {
        instance = new AuthenticationManager();
    }

    public static AuthenticationManager getInstance() {
        return instance;
    }

    //!
    public boolean authenticateUser(String username, String password) {
        return true;
    }

    //!
    public boolean registerUser(String username) {
        return true;
    }

    //!
    public boolean updateUserPassword(User user, String password) {
        return true;
    }

}
