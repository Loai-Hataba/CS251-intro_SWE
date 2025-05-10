package com.budgetapp.user;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.util.UUID;

public class AuthenticationManager {

    // The only instance of the manager
    private static AuthenticationManager instance ;

    // make the constructor private so it's only one instance will be created
    private AuthenticationManager() {}

    // static function for using the instance
    public static AuthenticationManager getInstance() {
        // check if the instance has been created or not
        if (instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }

    // private function to validate the entered password
    private boolean isValidPassword(String password) {

        // Regex to check for at least one digit, one uppercase letter, and a minimum length of 6 characters
        String pattern = "^(?=.*[A-Z])(?=.*\\d).{6,}$";

        return password.matches(pattern);
    }



    //! TODO : i need to know what it will do ??
    public boolean authenticateUser(String username, String password) {
        return true;
    }


    //! this function is to store the user into a database (currently we'll use json files )
    public boolean registerUser(String username , String password , String email , String phone , String country  ) {

        // first create a unique id for the user using uuid
        String userId = UUID.randomUUID().toString();
        // creating the user object
        User user = new User (userId , username , password , email ,country , phone );
        //! insert using the user manager
       boolean isDone =  UserManager.getInstance().insertUser(user);
       if (isDone) {
           System.out.println("User " + username + " has been registered and has a UUID: " + userId);
           return true;
       }
        System.out.println("User " + username + " has not been registered");
        return false;
    }

    //! I think it's need to be in the user manager
    public boolean updateUserPassword(String  username, String newPassword) {

        // now validate the password
        if(!isValidPassword(newPassword)) {
            System.out.println("The password is not valid");
            System.out.println("check if you have at least one character & one digit in the password");
            return false ;
        }
        // the password if valid then set it using the user.setPassword
       boolean isDone =  UserManager.getInstance().updateUserPassword(username,newPassword);
        if (isDone) {
            System.out.println("User " + username + " has been updated");
            return true;
        }
        System.out.println("User " + username + " has not been updated");
        return false ;
    }
    public boolean sendOTP (String phoneNumber ){
        if (phoneNumber.length()<11 || phoneNumber.length()>15) {
            System.out.println("The phone number is not valid");
            return false;
        }
        System.out.println("Simulating sending an OTP msg ");
        String otp = "123456" ;
        System.out.println("The OTP msg has been sent");
        return true;
    }

}
