package com.budgetapp.user;

import com.budgetapp.database.Records;
import com.budgetapp.methods.Methods;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.util.Random;
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

   



    public String authenticateUser(String username, String password) {
        String checkUser = UserManager.getInstance().checkUserCredentials(username, password);
        if (checkUser.equals("")) System.out.println("Username or Password are wrong.");
        return checkUser;
    }


    //! this function is to store the user into a database (currently we'll use json files )
    public String registerUser(String username , String password , String email , String phone , String country  ) {
        // check user doesn't exist already 
        boolean userAlreadyExists = UserManager.getInstance().checkUserExists(username);
        if (!userAlreadyExists)
        {
            while (true) {
                String sentOTP = sendOTP(phone);
                String otp = Methods.stringInput("Enter OTP: ", "^\\d{6}$", "OTP is a 6 digit Number");
                if(sentOTP.equals(otp)){
                    System.out.println("OTP is correct");
                    break;
                }
                System.out.println("OTP is incorrect, sending a new one...\n");
            }
            // first create a unique id for the user using uuid
            String userId = UUID.randomUUID().toString();
            // creating the user object
            User user = new User (userId , username , password , email ,country , phone );
            //! insert using the user manager
            Records rec = new Records(user);
            boolean isDone =  UserManager.getInstance().insertRecord(rec);
            if (isDone) {
               System.out.println("User " + username + " has been registered and has a UUID: " + userId);
               return userId;
            }
            System.out.println("User " + username + " has not been registered");
            return "";
        }
        else{
            System.out.println("User Already Exists!");
            return "";
        }
    }

    //! I think it's need to be in the user manager
    public boolean updateUserPassword(String  id, String newPassword) {

        // now validate the password
        if(!Methods.isValidPassword(newPassword)) {
            System.out.println("The password is not valid");
            System.out.println("check if you have at least one character & one digit in the password");
            return false ;
        }
        // the password if valid then set it using the user.setPassword
        boolean isDone =  UserManager.getInstance().updateRecordField(id, "password", newPassword);
        if (isDone) {
            System.out.println("User " + id + " has been updated");
            return true;
        }
        System.out.println("User " + id + " has not been updated");
        return false ;
    }
    public String sendOTP (String phoneNumber){
        System.out.println("The Otp has been Sent to your device...");
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        System.out.println("The OTP msg has been sent \n\n(" + otp + ")\n");
        return otp;
    }

}
