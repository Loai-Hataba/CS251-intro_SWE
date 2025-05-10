package com.budgetapp.methods;

import java.util.Scanner;

public class Methods {
    private static final Scanner scanner = new Scanner(System.in);
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
        return choice - '0';
    }

     // private function to validate the entered password
    public static boolean isValidPassword(String password) {

        // Regex to check for at least one digit, one uppercase letter, and a minimum length of 6 characters
        String pattern = "^(?=.*[A-Z])(?=.*\\d).{6,}$";

        return password.matches(pattern);
    }

    public static String stringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static void closeScanner(){
        scanner.close();
    }
}