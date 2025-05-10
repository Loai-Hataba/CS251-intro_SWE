package com.budgetapp.methods;

import java.util.Scanner;

public class Methods {
    public static int numInput(char begin, char end){
        Scanner scanner = new Scanner(System.in);
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
        scanner.close();
        return choice - '0';
    }
}