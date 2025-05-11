package com.budgetapp.system;

import java.util.logging.MemoryHandler;
import com.budgetapp.methods.Methods;

public class UI {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    BudgetSystem mySystem = new BudgetSystem();

    public UI() {
        System.out.println("UI object has been created!");
    }

    public void UImanager(){
        int start = startMenu();
        switch (start) {
            // sign up
            case 1:
                displaySignup();
                break;
            // login
            case 2:
                displayLogin();
                break;
        }
    }

    public int startMenu(){
    System.out.println(BLUE + "        __| |____________________________________________________________________________________| |__\r\n" + RESET + //
                       BLUE + "        __   ____________________________________________________________________________________   __\r\n" + RESET + //
                       BLUE + "          | |                                                                                    | |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  .______    __    __   _______   _______  _______ .___________.                    " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  |   _  \\  |  |  |  | |       \\ /  _____||   ____||           |                    " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  |  |_)  | |  |  |  | |  .--.  |  |  __  |  |__   `---|  |----`                    " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  |   _  <  |  |  |  | |  |  |  |  | |_ | |   __|      |  |                         " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  |  |_)  | |  `--'  | |  '--'  |  |__| | |  |____     |  |                         " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  |______/   \\______/  |_______/ \\______| |_______|    |__|                         " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "                                                                                    " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  .___  ___.      ___      .__   __.      ___       _______  _______ .______        " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  |   \\/   |     /   \\     |  \\ |  |     /   \\     /  _____||   ____||   _  \\       " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  |  \\  /  |    /  ^  \\    |   \\|  |    /  ^  \\   |  |  __  |  |__   |  |_)  |      " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  |  |\\/|  |   /  /_\\  \\   |  . `  |   /  /_\\  \\  |  | |_ | |   __|  |      /       " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  |  |  |  |  /  _____  \\  |  |\\   |  /  _____  \\ |  |__| | |  |____ |  |\\  \\----.  " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "          | |" + YELLOW + "  |__|  |__| /__/     \\__\\ |__| \\__| /__/     \\__\\ \\______| |_______|| _| `._____|  " + BLUE + "| |  \r\n" + RESET +  //
                       BLUE + "        __| |____________________________________________________________________________________| |__\r\n" + RESET +  //
                       BLUE + "        __   ____________________________________________________________________________________   __\r\n" + RESET +  //
                       BLUE + "          | |                                                                                    | |   " + RESET + //
                       "\n\n\n\n\n" + //
                       "                                    What would you like to do today?\n\n" + // 
                       "1)Sign UP\n" + //
                       "2)Log in\n" + //
                       "Choice: ");

        int choice = Methods.numInput('1', '7');

        return choice;
    }

    public void displayDashboard(){
        System.out.println("            Dina Dashboard hahaha");
        System.out.println("\n\n1)Income" + //
        "2)Expense");
        int choice = Methods.numInput('1', '2');
        switch (choice) {
            case 1:
                displayIncome();
                break;
            case 2:
                displayExpense();
                break;
            default:
                break;
        }

    }

    public void displaySignup(){
        while (true){
            System.out.println("                            ===================================");
            System.out.println("                            |           SIGN UP MENU          |");
            System.out.println("                            ===================================");
            System.out.println("                            |                                 |");
            System.out.println("                            |  Please enter your credentials  |");
            System.out.println("                             ----------------------------------\n");
            String userName = Methods.stringInput("Username: ", "^[a-zA-Z][^\\\\/\\s]{0,9}$", "Username shall have: "+ //
                            "\n   * Start with a letter\r\n" + //
                            "   * No spaces\r\n" + //
                            "   * No backslashes (\\)\r\n" + //
                            "   * No forward slashes (/)\r\n" + //
                            "   * Maximum 10 characters");
            String password, confirmPassword;
            while (true) {
                password = Methods.passwordInput("Password: ");
                confirmPassword = Methods.passwordInput("Confirm Password: ");
                if (password.equals(confirmPassword)) {
                    break;
                } else {
                    System.out.println("Passwords do not match. Please try again.");
                }
            }
            String email = Methods.stringInput("Email Address : ", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", "Email shall be in the form \"name@domain.com\"");
            String phoneNum = Methods.stringInput("Phone Number (01...): ", "^01\\d{9}$", "PhoneNumber should start with 01 and be 11 digits long");
            String country = Methods.stringInput("Country: ");
            System.out.println("credentials: " + userName + " " + password + " " + phoneNum + " " + email + " " + country);
            boolean register = mySystem.register(userName, password, phoneNum, email, country);
            if (register)
            {
                System.out.println("signed in...");
                displayDashboard();
                // show dashboard
                break;
            }
        }
    }

    public void displayLogin(){
        while (true){
            System.out.println("                            ===================================");
            System.out.println("                            |           LOG IN MENU           |");
            System.out.println("                            ===================================");
            System.out.println("                            |                                 |");
            System.out.println("                            |  Please enter your credentials  |");
            System.out.println("                             ----------------------------------\n");
            String userName = Methods.stringInput("Username: ", "^[a-zA-Z][^\\\\/\\s]{0,9}$", "Username shall have: "+ //
                            "\n   * Start with a letter\r\n" + //
                            "   * No spaces\r\n" + //
                            "   * No backslashes (\\)\r\n" + //
                            "   * No forward slashes (/)\r\n" + //
                            "   * Maximum 10 characters");
            String password = Methods.passwordInput("Password: ");
            boolean login = mySystem.authenticate(userName, password);
            if(login)
                displayDashboard();
                System.out.println("logged in");
                // display dashboard
                break;
            }
    }
    public void displayIncome(){
        List
        
    }

    public void displayBudget(){
        
    }
    
    public void displayExpense(){
        
    }

    public void displayReminder(){
        
    }

    public boolean verifyEmail(String email){
        return true;
    }

    public boolean authenticate(String username, String password){
        return true;
    }

    public boolean addIncome(String incomeSource, float amount){
        return true;
    }    

    public boolean addBudget(String category, float amount){
        return true;
    }

    public boolean addReminder(String reminderName,String time){
        return true;
    }

    public boolean addExpense(String category, float amount, String date){
        return true;
    }

    public void showMessage(String message){

    }

    public boolean verifyOTP(int OTP){
        return true;
    }
    
}