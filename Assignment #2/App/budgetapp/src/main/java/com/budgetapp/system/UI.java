package com.budgetapp.system;

import java.util.List;

import com.budgetapp.methods.Methods;

public class UI {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    BudgetSystem mySystem = new BudgetSystem();

    public UI(){}

    public void UImanager() {
        while(true){
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
    }

    public void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void wait(int milliSeconds){
        try{
            Thread.sleep(milliSeconds);
        }catch (InterruptedException e) {
                e.printStackTrace();
        }
    }

    public int startMenu() {
        clearScreen();
        System.out.println(BLUE + "        __| |____________________________________________________________________________________| |__\r\n" + RESET
                + //
                BLUE + "        __   ____________________________________________________________________________________   __\r\n" + RESET
                + //
                BLUE + "          | |                                                                                    | |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  .______    __    __   _______   _______  _______ .___________.                    " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  |   _  \\  |  |  |  | |       \\ /  _____||   ____||           |                    " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  |  |_)  | |  |  |  | |  .--.  |  |  __  |  |__   `---|  |----`                    " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  |   _  <  |  |  |  | |  |  |  |  | |_ | |   __|      |  |                         " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  |  |_)  | |  `--'  | |  '--'  |  |__| | |  |____     |  |                         " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  |______/   \\______/  |_______/ \\______| |_______|    |__|                         " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "                                                                                    " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  .___  ___.      ___      .__   __.      ___       _______  _______ .______        " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  |   \\/   |     /   \\     |  \\ |  |     /   \\     /  _____||   ____||   _  \\       " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  |  \\  /  |    /  ^  \\    |   \\|  |    /  ^  \\   |  |  __  |  |__   |  |_)  |      " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  |  |\\/|  |   /  /_\\  \\   |  . `  |   /  /_\\  \\  |  | |_ | |   __|  |      /       " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  |  |  |  |  /  _____  \\  |  |\\   |  /  _____  \\ |  |__| | |  |____ |  |\\  \\----.  " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "          | |" + YELLOW + "  |__|  |__| /__/     \\__\\ |__| \\__| /__/     \\__\\ \\______| |_______|| _| `._____|  " + BLUE + "| |  \r\n" + RESET
                + //
                BLUE + "        __| |____________________________________________________________________________________| |__\r\n" + RESET
                + //
                BLUE + "        __   ____________________________________________________________________________________   __\r\n" + RESET
                + //
                BLUE + "          | |                                                                                    | |   " + RESET
                + //
                "\n\n\n");
                System.out.println("                                        " + BLUE + "================================");
                System.out.println("                                        " + BLUE + "|         " + YELLOW + "WELCOME USER!        " + BLUE + "|" + RESET);
                System.out.println("                                        " + BLUE + "|        ===============       |");
                System.out.println("                                        " + BLUE + "|                              |");
                System.out.println("                                        " + BLUE + "|______________________________|");
                System.out.println("                                        " + BLUE + "|  " + YELLOW + "1. Sign Up" + RESET + BLUE+ "   | " + YELLOW + " 2. Log In" + BLUE + "   |" + RESET);
                System.out.println("                                        " + BLUE + "================================" + YELLOW );
                System.out.print("Enter your choice (1 or 2): ");


        int choice = Methods.numInput('1', '2');

        return choice;
    }

    public void displayDashboard() {
        while (true) {
            clearScreen();
            System.out.println("                        "+ BLUE +" ╔════════════════════════════════════════════════════════════╗");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                 "+ YELLOW +"DINA DASH-BOARD"+ BLUE +"                            ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ╠════════════════════════════════════════════════════════════╣");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                     "+ YELLOW +"(1) Display Income"+ BLUE +"                     ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(2) Display Expenses"+ BLUE +"                  ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(3) Display Budgets"+ BLUE +"                   ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(4) Display Reminders"+ BLUE +"                 ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(5) Logout"+ BLUE +"                            ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(6) Exit"+ BLUE +"                              ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ╚════════════════════════════════════════════════════════════╝" + YELLOW);
            System.out.print("\n                        Choice: ");
            int choice = Methods.numInput('1', '6');
                switch (choice) {
                    case 1:
                        System.out.println("Displaying income...");
                        wait(3000);
                        displayIncome();
                        break;
                    case 2:
                        System.out.println("Displaying expenses...");
                        wait(3000);
                        displayExpense();
                        break;
                    case 3:
                        System.out.println("Displaying budgets...");
                        wait(3000);
                        displayBudget();
                        break;
                    case 4:
                        System.out.println("Displaying reminders...");
                        wait(3000);
                        displayReminder();
                        break;
                    case 5:
                        System.out.println("Logging out...");
                        mySystem.clearUUID();
                        wait(3000);
                        return;
                    case 6:
                        System.out.println("Exiting...\nGoodbye!!");
                        System.exit(0);
                        break;
                    default:
                        break;
                }
        }

    }

    public void displaySignup() {
        while (true) {
            clearScreen();
            System.out.println("                                        " + BLUE + "================================");
            System.out.println("                                        " + BLUE + "|          " + YELLOW + "SIGN UP MENU " + BLUE + "       |");
            System.out.println("                                        " + BLUE + "================================");
            System.out.println("                                        " + BLUE + "|                              |");
            System.out.println("                                        " + BLUE + "|   " + YELLOW + "Please enter credentials" + BLUE + "   |");
            System.out.println("                                        " + BLUE + "--------------------------------\n " + YELLOW);
            String userName = Methods.stringInput("Username: ", "^[a-zA-Z][^\\\\/\\s]{0,15}$", """
                                                                                              Username shall have: 
                                                                                                 * Start with a letter\r
                                                                                                 * No spaces\r
                                                                                                 * No backslashes (\\)\r
                                                                                                 * No forward slashes (/)\r
                                                                                                 * Maximum 15 characters""");
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
            String email = Methods.stringInput("Email Address : ", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                    "Email shall be in the form \"name@domain.com\"");
            String phoneNum = Methods.stringInput("Phone Number (01...): ",
                    "^01\\d{9}$", "PhoneNumber should start with 01 and be 11 digits long");
            String country = Methods.stringInput("Country: ");
            boolean register = mySystem.register(userName, password, phoneNum, email, country);
            System.out.println("\nRegister status: " + register);
            if (register) {
                displayDashboard();
                break;
            }else{
                System.out.println("Press Enter...");
                // dumb way to press enter
                Methods.enterInput();
            }
        }
    }

    public void displayLogin() {
        while (true) {
            clearScreen();
            System.out.println("                                        " + BLUE + "================================");
            System.out.println("                                        " + BLUE + "|          " + YELLOW + "LOG IN MENU " + BLUE + "        |");
            System.out.println("                                        " + BLUE + "================================");
            System.out.println("                                        " + BLUE + "|                              |");
            System.out.println("                                        " + BLUE + "|   " + YELLOW + "Please enter credentials" + BLUE + "   |");
            System.out.println("                                        " + BLUE + "--------------------------------\n " + YELLOW);
            String userName = Methods.stringInput("Username: ", "^[a-zA-Z][^\\\\/\\s]{0,15}$", "Username shall have: " + "\n   * Start with a letter\r\n" + "   * No spaces\r\n" + "   * No backslashes (\\)\r\n" + "   * No forward slashes (/)\r\n" + "   * Maximum 15 characters");
            String password = Methods.passwordInput("Password: ");
            boolean login = mySystem.authenticate(userName, password);
            if (login) {
                displayDashboard();
                break;
            }else{
                System.out.println("Press Enter...");
                // dumb way to press enter
                Methods.enterInput();
            }
        }
    }

    public void displayIncome() {
        while (true) {
            clearScreen();
            List<String> incomeRecords = mySystem.fetchIncome();
            System.out.println("\n\n\n                                                   Fetching Data...");
            wait(2000);
            clearScreen();
            System.out.println("\n═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
            if (!incomeRecords.isEmpty()) {

                System.out.println("                                                    \nIncome Records: \n");
                for (String record : incomeRecords) {
                    System.out.println(record);
                }
            } else {
                System.out.println("\n                                              \nNo Income Data yet.\n");
            }
            System.out.println("\n═══════════════════════════════════════════════════════════════════════════════════════════════════════════");

            System.out.println("                        "+ BLUE +" ╔════════════════════════════════════════════════════════════╗");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                           "+ YELLOW +"    INCOME"+ BLUE +"                       ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ╠════════════════════════════════════════════════════════════╣");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(1) Add Income"+ BLUE +"                        ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(2) Edit Income"+ BLUE +"                       ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(3) Delete Income"+ BLUE +"                     ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(4) Go back to Dashboard"+ BLUE +"              ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ╚════════════════════════════════════════════════════════════╝" + YELLOW);
            System.out.print("\n                        Choice: ");
            int choice = Methods.numInput('1', '4');
            switch (choice) {
                case 1:
                    System.out.print("Add Income\n");
                    String source = Methods.stringInput("Enter Income Source: ");
                    double amount = Methods.doubleInput("Enter Income amount: ");
                    String category = Methods.stringInput("Enter Income Category: ");
                    String date = Methods.dateInput();
                    System.out.print("0)One time       1)Recurring\nAnswer: ");
                    int isRecurring = Methods.numInput('0', '1');
                    mySystem.addIncome(source, amount, category, date, isRecurring);
                    System.out.println("Added Income Successfully...");
                    wait(3000);
                    break;
                case 2:
                    System.out.println("Edit Income\n");
                    System.out.print("Enter Income ID: ");
                    int editIncomeId = Methods.numInput();
                    String editSource = Methods.stringInput("Enter Income Source: ");
                    double editAmount = Methods.doubleInput("Enter Income amount: ");
                    String editCategory = Methods.stringInput("Enter Income Category: ");
                    String editDate = Methods.dateInput();
                    System.out.print("0)One time       1)Recurring\nAnswer: ");
                    int editIsRecurring = Methods.numInput('0', '1');
                    mySystem.editIncome(editSource, editIncomeId, editAmount, editCategory, editDate, editIsRecurring);
                    System.out.println("Edited Income Successfully...");
                    wait(3000);
                    break;
                case 3:
                    System.out.println("Delete Income\n");
                    System.out.print("Enter Income ID: ");
                    int deleteIncomeId = Methods.numInput();
                    while(true){
                        if (mySystem.deleteIncome(deleteIncomeId)){
                            System.out.println("Deleted Income Successfully...");
                            break;
                        } else {
                            System.out.print("Enter Income ID: ");
                            deleteIncomeId = Methods.numInput();
                        }
                    }
                    wait(3000);
                    break;
                case 4:
                    return;
            }
        }
    }

    public void displayExpense() {
        while (true) {
            clearScreen();
            List<String> expenseRecords = mySystem.fetchExpense();
            System.out.println("\n\n\n                                                   Fetching Data...");
            wait(2000);
            clearScreen();
            System.out.println("\n═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
            if (!expenseRecords.isEmpty()) {

                System.out.println("                                                    \nExpense Records: \n");
                for (String record : expenseRecords) {
                    System.out.println(record);
                }
            } else {
                System.out.println("\n                                              \nNo Expense Data yet.\n");
            }
            System.out.println("\n═══════════════════════════════════════════════════════════════════════════════════════════════════════════");

            System.out.println("                        "+ BLUE +" ╔════════════════════════════════════════════════════════════╗");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                           "+ YELLOW +"    Expense"+ BLUE +"                       ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ╠════════════════════════════════════════════════════════════╣");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(1) Add Expense"+ BLUE +"                        ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(2) Edit Expense"+ BLUE +"                       ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(3) Delete Expense"+ BLUE +"                     ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(4) Go back to Dashboard"+ BLUE +"              ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ╚════════════════════════════════════════════════════════════╝" + YELLOW);
            System.out.print("\n                        Choice: ");
            int choice = Methods.numInput('1', '4');
            switch (choice) {
                case 1:
                    System.out.print("Add Expense\n");
                    String source = Methods.stringInput("Enter Expense Source: ");
                    double amount = Methods.doubleInput("Enter Expense amount: ");
                    String category = Methods.stringInput("Enter Expense Category: ");
                    String date = Methods.dateInput();
                    System.out.print("0)One time       1)Recurring\nAnswer: ");
                    int isRecurring = Methods.numInput('0', '1');
                    mySystem.addExpense(source, amount, category, date, isRecurring);
                    System.out.println("Added Expense Successfully...");
                    wait(3000);
                    break;
                case 2:
                    System.out.println("Edit Expense\n");
                    System.out.print("Enter Expense ID: ");
                    int editExpenseId = Methods.numInput();
                    String editSource = Methods.stringInput("Enter Expense Source: ");
                    double editAmount = Methods.doubleInput("Enter Expense amount: ");
                    String editCategory = Methods.stringInput("Enter Expense Category: ");
                    String editDate = Methods.dateInput();
                    System.out.print("\n0)One time       1)Recurring\nAnswer: ");
                    int editIsRecurring = Methods.numInput('0', '1');
                    mySystem.editExpense(editSource, editExpenseId, editAmount, editCategory, editDate, editIsRecurring);
                    System.out.println("Edited Expense Successfully...");
                    wait(3000);
                    break;
                case 3:
                    System.out.println("Delete Expense\n");
                    System.out.print("Enter Expense ID: ");
                    int deleteExpenseId = Methods.numInput();
                    while(true){
                        if (mySystem.deleteExpense(deleteExpenseId)){
                            System.out.println("Deleted Income Successfully...");
                            break;
                        } else {
                            System.out.print("Enter Income ID: ");
                            deleteExpenseId = Methods.numInput();
                        }
                    }
                    wait(3000);
                    break;
                case 4:
                    return;
            }
        }
    }

    public void displayBudget() {
        while (true) {
            clearScreen();
            List<String> budgetRecords = mySystem.fetchBudget();
            System.out.println("\n\n\n                                                   Fetching Data...");
            wait(2000);
            clearScreen();
            System.out.println("\n═══════════════════════════════════════════════════════════════════════════════════════════════════════════");
            if (!budgetRecords.isEmpty()) {

                System.out.println("                                                    \nBudget Records: \n");
                for (String record :budgetRecords) {
                    System.out.println(record);
                }
            } else {
                System.out.println("\n                                              \nNo Budget Data yet.\n");
            }
            System.out.println("\n═══════════════════════════════════════════════════════════════════════════════════════════════════════════");

            System.out.println("                        "+ BLUE +" ╔════════════════════════════════════════════════════════════╗");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                           "+ YELLOW +"    Budget"+ BLUE +"                       ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ╠════════════════════════════════════════════════════════════╣");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(1) Add Budget"+ BLUE +"                        ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(2) Edit Budget"+ BLUE +"                       ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(3) Delete Budget"+ BLUE +"                     ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ║                      "+ YELLOW +"(4) Go back to Dashboard"+ BLUE +"              ║");
            System.out.println("                        "+ BLUE +" ║                                                            ║");
            System.out.println("                        "+ BLUE +" ╚════════════════════════════════════════════════════════════╝" + YELLOW);
            System.out.print("\n                        Choice: ");
            int choice = Methods.numInput('1', '4');
            switch (choice) {
                case 1:
                    System.out.print("Add Budget\n");
                    String source = Methods.stringInput("Enter Budget Source: ");
                    double amount = Methods.doubleInput("Enter Budget amount: ");
                    String category = Methods.stringInput("Enter Budget Category: ");
                    System.out.print("Start Date\n");
                    String startDate = Methods.dateInput();
                    System.out.print("End Date\n");
                    String endDate = Methods.dateInput();
                    mySystem.addBudget(source, amount, category, startDate, endDate);
                    System.out.println("Added Budget Successfully...");
                    wait(3000);
                    break;
                case 2:
                    System.out.println("Edit Budget\n");
                    System.out.print("Enter Budget ID: ");
                    int editBudgetId = Methods.numInput();
                    String editSource = Methods.stringInput("Enter Budget Source: ");
                    double editAmount = Methods.doubleInput("Enter Budget amount: ");
                    String editCategory = Methods.stringInput("Enter Budget Category: ");
                    System.out.print("Start Date\n");
                    String editStartDate = Methods.dateInput();
                    System.out.print("End Date\n");
                    String editEndDate = Methods.dateInput();
                    mySystem.editBudget(editSource, editBudgetId, editAmount, editCategory, editStartDate, editEndDate);
                    System.out.println("Edited Budget Successfully...");
                    wait(3000);
                    break;
                case 3:
                    System.out.println("Delete Budget\n");
                    System.out.print("Enter Budget ID: ");
                    int deleteBudgetId = Methods.numInput();
                    while(true){
                        if (mySystem.deleteIncome(editBudgetId)){
                            System.out.println("Deleted Budget Successfully...");
                            break;
                        } else {
                            System.out.print("Enter Budget ID: ");
                            editBudgetId = Methods.numInput();
                        }
                    }
                    wait(3000);
                    break;
                case 4:
                    return;
            }

            // //////////////////////////////////////////////////////////////////////////////////////////////////
            // List<String> budgetRecords = mySystem.fetchBudget();
            // System.out.println("\n-----------------------------------------------------------------------------------------------------------");
            // if (!budgetRecords.isEmpty()) {
            //     System.out.println("Budget Records: \n");
            //     for (String record : budgetRecords) {
            //         System.out.println(record);
            //     }
            // } else {
            //     System.out.println("\n              No Budget Data yet.\n");
            // }
            // System.out.println("\n-----------------------------------------------------------------------------------------------------------");
            // System.out.println("What do you want to do: \n\n1)Add Budget\n2)Edit Budget\n3)Delete Budget\n4)Dashboard\nChoice: ");
            // int choice = Methods.numInput('1', '4');
            // switch (choice) {
            //     case 1:
            //         System.out.println("Add Budget:\n");
            //         String source = Methods.stringInput("Enter Budget Source: ");
            //         double amount = Methods.doubleInput("Enter Budget amount: ");
            //         String category = Methods.stringInput("Enter Budget Category: ");
            //         System.out.print("Start Date\n");
            //         String startDate = Methods.dateInput();
            //         System.out.print("End Date\n");
            //         String endDate = Methods.dateInput();
            //         mySystem.addBudget(source, amount, category, startDate, endDate);
            //         break;
            //     case 2:
            //         System.out.println("Edit Budget:\n");
            //         System.out.println("Enter Budget ID: ");
            //         int editBudgetId = Methods.numInput();
            //         String editSource = Methods.stringInput("Enter Budget Source: ");
            //         double editAmount = Methods.doubleInput("Enter Budget amount: ");
            //         String editCategory = Methods.stringInput("Enter Budget Category: ");
            //         System.out.print("Start Date\n");
            //         String editStartDate = Methods.dateInput();
            //         System.out.print("End Date\n");
            //         String editEndDate = Methods.dateInput();
            //         mySystem.editBudget(editSource, editBudgetId, editAmount, editCategory, editStartDate, editEndDate);
            //         break;
            //     case 3:
            //         System.out.println("Delete Budget:\n");
            //         System.out.println("Enter Budget ID: ");
            //         int deleteBudgetId = Methods.numInput();
            //         mySystem.deleteBudget(deleteBudgetId);
            //         break;
            //     case 4:
            //         return;
            // }
        }
    }

    public void displayReminder() {
        while (true) {
            List<String> reminderRecords = mySystem.fetchReminder();
            System.out.println("\n-----------------------------------------------------------------------------------------------------------");
            if (!reminderRecords.isEmpty()) {
                System.out.println("Reminder Records: \n");
                for (String record : reminderRecords) {
                    System.out.println(record);
                }
            } else {
                System.out.println("\n                No Reminder Data yet.\n");
            }
            System.out.println("\n-----------------------------------------------------------------------------------------------------------");
            System.out.println("What do you want to do: \n\n1)Add Reminder\n2)Edit Reminder\n3)Delete Reminder\n4)Dashboard\nChoice: ");
            int choice = Methods.numInput('1', '4');
            switch (choice) {
                case 1:
                    System.out.println("Add Reminder:\n");
                    String title = Methods.stringInput("Enter Reminder Title: ");
                    String description = Methods.stringInput("Enter Reminder Description: ");
                    String time = Methods.timeInput();
                    String date = Methods.dateInput();
                    mySystem.addReminder(title, description, date, time);
                    break;
                case 2:
                    System.out.println("Edit Reminder:\n");
                    System.out.println("Enter Reminder ID: ");
                    int editReminderId = Methods.numInput();
                    String editTitle = Methods.stringInput("Enter Reminder Title: ");
                    String editDescription = Methods.stringInput("Enter Reminder Description: ");
                    String editTime = Methods.timeInput();
                    String editDate = Methods.dateInput();
                    mySystem.editReminder(editTitle, editReminderId, editDescription, editDate, editTime);
                    break;
                case 3:
                    System.out.println("Delete Reminder:\n");
                    System.out.println("Enter Reminder ID: ");
                    int deleteReminderId = Methods.numInput();
                    mySystem.deleteReminder(deleteReminderId);
                    break;
                case 4:
                    return;
            }
        }
    }

    public void showMessage(String message) {

    }
}
