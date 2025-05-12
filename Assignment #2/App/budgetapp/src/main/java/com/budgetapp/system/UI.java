package com.budgetapp.system;

import com.budgetapp.methods.Methods;
import java.util.List;
import java.util.ArrayList;

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
        while(true){
            System.out.println("            Dina Dash board hahaha");
            System.out.println("\n\n1)Income\n" + //
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

    }

    public void displaySignup(){
        while (true){
            System.out.println("                            ===================================");
            System.out.println("                            |           SIGN UP MENU          |");
            System.out.println("                            ===================================");
            System.out.println("                            |                                 |");
            System.out.println("                            |  Please enter your credentials  |");
            System.out.println("                             ----------------------------------\n");
            String userName = Methods.stringInput("Username: ", "^[a-zA-Z][^\\\\/\\s]{0,9}$", "Username shall have: "+ "\n   * Start with a letter\r\n" +  "   * No spaces\r\n" +  "   * No backslashes (\\)\r\n" +  "   * No forward slashes (/)\r\n" +  "   * Maximum 10 characters");
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
            String userName = Methods.stringInput("Username: ", "^[a-zA-Z][^\\\\/\\s]{0,9}$", "Username shall have: "+"\n   * Start with a letter\r\n" + "   * No spaces\r\n" + "   * No backslashes (\\)\r\n" + "   * No forward slashes (/)\r\n" + "   * Maximum 10 characters");
            String password = Methods.passwordInput("Password: ");
            boolean login = mySystem.authenticate(userName, password);
            System.out.println("Login status " + login);
            if(login){
                    displayDashboard();
                    break;
                }
            }
    }
    public void displayIncome(){
        while (true){
            List<String> incomeRecords = mySystem.fetchIncome();
            if (!incomeRecords.isEmpty())
            {
                System.out.println("Income Records: ");
                for (String record : incomeRecords){
                    System.out.println(record);
                } 
            }
            else {
                System.err.println("No Income Data yet.\n\n");
            }
            System.out.println("What do you want to do: \n\n1)Add Income\n2)Edit Income\n3)Delete Income\n4)Dashboard\nChoice: ");
            int choice = Methods.numInput('1', '4');
            switch (choice) {
                case 1:
                    System.out.println("Add Income:\n");
                    String source = Methods.stringInput("Enter Income Source: ");
                    double amount = Methods.doubleInput("Enter Income amount: ");
                    String category = Methods.stringInput("Enter Income Category: ");
                    String date = Methods.dateInput();
                    System.out.println("Is it Recurring: \n0)No\n1)Yes\nAnswer: ");
                    int isRecurring = Methods.numInput('0', '1');
                    mySystem.addIncome(source, amount, category, date, isRecurring);
                    break;
                case 2:
                    System.out.println("Edit Income:\n");
                    System.out.println("Enter Income ID: ");
                    int editIncomeId = Methods.numInput();
                    String editSource = Methods.stringInput("Enter Income Source: ");
                    double editAmount = Methods.doubleInput("Enter Income amount: ");
                    String editCategory = Methods.stringInput("Enter Income Category: ");
                    String editDate = Methods.dateInput();
                    System.out.println("Is it Recurring: \n0)No\n1)Yes\nAnswer: ");
                    int editIsRecurring = Methods.numInput('0', '1');
                    mySystem.editIncome(editSource, editIncomeId, editAmount, editCategory, editDate, editIsRecurring);
                    break;
                case 3:
                    System.out.println("Delete Income:\n");
                    System.out.println("Enter Income ID: ");
                    int deleteIncomeId = Methods.numInput();
                    mySystem.deleteIncome(deleteIncomeId);
                    break;
                case 4:
                    return;
            }   
        }
    }

    
    public void displayExpense(){
        while (true){
            List<String> expenseRecords = mySystem.fetchExpense();
            if (!expenseRecords.isEmpty())
            {
                System.out.println("Expense Records: ");
                for (String record : expenseRecords){
                    System.out.println(record);
                } 
            }
            else {
                System.err.println("No expense Data yet.\n\n");
            }
            System.out.println("What do you want to do: \n\n1)Add Expense\n2)Edit Expense\n3)Delete Expense\n4)Dashboard\nChoice: ");
            int choice = Methods.numInput('1', '4');
            switch (choice) {
                case 1:
                System.out.println("Add Expense:\n");
                String source = Methods.stringInput("Enter Expense Source: ");
                double amount = Methods.doubleInput("Enter Expense amount: ");
                String category = Methods.stringInput("Enter Expense Category: ");
                String date = Methods.dateInput();
                System.out.println("Is it Recurring: \n0)No\n1)Yes\nAnswer: ");
                int isRecurring = Methods.numInput('0', '1');
                mySystem.addExpense(source, amount, category, date, isRecurring);
                break;
                case 2:
                System.out.println("Edit Expense:\n");
                System.out.println("Enter Expense ID: ");
                int editExpenseId = Methods.numInput();
                String editSource = Methods.stringInput("Enter Expense Source: ");
                double editAmount = Methods.doubleInput("Enter Expense amount: ");
                String editCategory = Methods.stringInput("Enter Expense Category: ");
                String editDate = Methods.dateInput();
                System.out.println("Is it Recurring: \n0)No\n1)Yes\nAnswer: ");
                int editIsRecurring = Methods.numInput('0', '1');
                mySystem.editExpense(editSource, editExpenseId, editAmount, editCategory, editDate, editIsRecurring);
                break;
                case 3:
                System.out.println("Delete Expense:\n");
                System.out.println("Enter Expense ID: ");
                int deleteExpenseId = Methods.numInput();
                mySystem.deleteExpense(deleteExpenseId);
                break;
                case 4:
                return;
            }   
        }
    }
    
    public void displayBudget(){
        
    }
    public void displayReminder(){
        
    }

    public boolean authenticate(String username, String password){
        return true;
    }

    public void showMessage(String message){

    }
}