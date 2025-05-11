package com.budgetapp.methods;

import java.io.Console;
import java.util.Scanner;

public class Methods {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Console console = System.console();

    public static int numInput(char begin, char end){
        char choice;

        while (true){
            String input = scanner.nextLine();
            if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
                choice = input.charAt(0);

                if (choice >= begin && choice <= end) {
                    System.out.println("You chose option: " + choice);
                    break;
                } else {
                    System.out.println("Please enter a digit between " + begin + " and " + end + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        //converting the char into number
        return choice - '0';
    }

     // private function to validate the entered password
    public static boolean isValidPassword(String password) {

        // Regex to check for at least one digit, one uppercase letter, and a minimum length of 6 characters
        String pattern = "^(?=.*[A-Z])(?=.*\\d).{6,}$";

        return password.matches(pattern);
    }

    public static String stringInput(String prompt) {
        return stringInput(prompt, null, null);
    }

    public static String stringInput(String prompt, String regexPattern, String regexMessage) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (regexPattern == null || input.matches(regexPattern)) {
                return input;
            } else {
                System.out.println(regexMessage);
            }
        }
    }

    public static String passwordInput(String prompt){
        /* console only */
        // if (console == null) {
        //     throw new IllegalStateException("Console not available. Please run from a supported terminal.");
        // }
        String psswrd;
        while (true){
            System.out.print(prompt);
            psswrd = scanner.nextLine();
            // console only
            // char[] password = console.readPassword(prompt);
            // psswrd = new String(password); 
            if (!isValidPassword(psswrd)){
                System.out.println("Password shall contain at least one digit, one uppercase letter, and a minimum length of 6 characters");
            }
            else break;
        }
        return new String(psswrd);
    }

    public static void closeScanner(){
        scanner.close();
    }
}