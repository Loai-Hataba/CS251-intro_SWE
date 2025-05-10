package com.budgetapp.system;

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

    }

    //FIXME: pass to display function user Object
    public void displaySignup(){
        System.out.println("sign up pleaseooo");
        String username = "Loai Hataba";
        String password = "123";
        String phoneNum = "+01275397858";
        String email = "lolooo@spacetoon.com";
        String country = "koko";
        mySystem.register(username, password, phoneNum, email, country);


        // print menu
        // ask for crednetials
        // send credentials to auth manager
        // auth man sends otp
        // ui verify otp function
        // send otp boolean to auth manager
        // auth manager send to user manager to create user
        // user manager creates user
    }

    public void displayLogin(){
        
    }
    public void displayIncome(){
        
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