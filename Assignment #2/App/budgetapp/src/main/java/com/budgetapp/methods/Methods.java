package com.budgetapp.methods;

import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Scanner;

import com.budgetapp.database.Records;
import com.google.gson.Gson;

public class Methods {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Console console = System.console();

    public static float floatInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                float value = Float.parseFloat(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive number.");
            }
        }
    }

    public static double doubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive number.");
            }
        }
    }

    public static int numInput() {
        char choice = ' ';
        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
                choice = input.charAt(0);
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            //converting the char into number
            return choice - '0';
        }
    }

    public static int numInput(char begin, char end) {
        char choice;

        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
                choice = input.charAt(0);

                if (choice >= begin && choice <= end) {
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

    //! date validate until 2025
    public static String dateInput() {
        String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$";
        String errorMsg = "Invalid date format. Please enter as DD/MM/YYYY.";
        return stringInput("Enter date (DD/MM/YYYY): ", datePattern, errorMsg);
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

    public static String passwordInput(String prompt) {
        /* console only */
        // if (console == null) {
        //     throw new IllegalStateException("Console not available. Please run from a supported terminal.");
        // }
        String psswrd;
        while (true) {
            System.out.print(prompt);
            psswrd = scanner.nextLine();
            // console only
            // char[] password = console.readPassword(prompt);
            // psswrd = new String(password); 
            if (!isValidPassword(psswrd)) {
                System.out.println("Password shall contain at least one digit, one uppercase letter, and a minimum length of 6 characters");
            } else {
                break;
            }
        }
        return new String(psswrd);
    }

    private static final String RECORDS_FILE = "Assignment #2\\App\\budgetapp\\src\\main\\java\\com\\budgetapp\\database\\database.json";
    private static final HashMap<String, Records> records = new HashMap<>();
    private static final Gson gson = GsonTool.getGson();

    public static HashMap<String, Records> loadRecords() {
        // Load all database rows into an array of record objects
        File file = new File(RECORDS_FILE);
        if (file.exists() && file.length() > 0) {
            try (Reader reader = new FileReader(file)) {
                Records[] recordsArray = gson.fromJson(reader, Records[].class);
                if (recordsArray != null) {
                    records.clear();
                    for (Records rec : recordsArray) {
                        if (rec.id != null) {
                            records.put(rec.id, rec);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return records;
    }

    private static void saveRecordsToFile() {
        try (Writer writer = new FileWriter(RECORDS_FILE)) {
            gson.toJson(records.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //! function to update a field in the record
    public static boolean updateRecordField(String id, String fieldName, Object newValue) {
        Records rec = records.get(id);
        if (rec == null) {
            System.out.println("record not found");
            return false;
        }
        try {
            Field field = rec.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            // For List fields or any other type, always replace the value with newValue
            field.set(rec, newValue);
            saveRecordsToFile();
            System.out.println("Field '" + fieldName + "' updated for record with id: " + id);
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Failed to update field: " + fieldName);
            e.printStackTrace();
            return false;
        }
    }

    // Insert record into memory list (and you can extend this to save to file)
    public static boolean insertRecord(Records rec) {
        if (rec.id == null || records.containsKey(rec.id)) {
            return false;
        }
        records.put(rec.id, rec);
        saveRecordsToFile();
        System.out.println("The records file has been updated ");
        return true;
    }

    public static boolean deleteRecord(String id) {
        if (records.containsKey(id)) {
            records.remove(id);
            saveRecordsToFile();
            return true;
        }
        System.out.println("record not found");
        return false;
    }

    public static Records getRecordById(String id) {
        return records.get(id);
    }

    public static void closeScanner() {
        scanner.close();
    }
}
