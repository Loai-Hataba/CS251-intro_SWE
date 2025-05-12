package com.budgetapp.methods;

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

/**
 * Utility class providing common methods for input handling and database
 * operations. This class contains static methods for various input validations
 * and database operations.
 */
public class Methods {

    /**
     * Scanner instance for handling console input
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads and validates a float input from the user.
     *
     * @param prompt The message to display to the user
     * @return A positive float value
     */
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

    /**
     * Reads and validates a double input from the user.
     *
     * @param prompt The message to display to the user
     * @return A positive double value
     */
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

    /**
     * Reads and validates a single digit numeric input.
     *
     * @return The numeric value as an integer
     */
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

    /**
     * Reads and validates a numeric input within a specified range.
     *
     * @param begin The lower bound of valid input (inclusive)
     * @param end The upper bound of valid input (inclusive)
     * @return The validated numeric input as an integer
     */
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

    /**
     * Reads and validates a date input in DD/MM/YYYY format.
     *
     * @return A validated date string
     */
    public static String dateInput() {
        String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$";
        String errorMsg = "Invalid date format. Please enter as DD/MM/YYYY.";
        return stringInput("Enter date (DD/MM/YYYY): ", datePattern, errorMsg);
    }

    /**
     * Validates if a password meets the required criteria.
     *
     * @param password The password to validate
     * @return true if password meets criteria, false otherwise
     */
    public static boolean isValidPassword(String password) {

        // Regex to check for at least one digit, one uppercase letter, and a minimum length of 6 characters
        String pattern = "^(?=.*[A-Z])(?=.*\\d).{6,}$";

        return password.matches(pattern);
    }

    /**
     * Reads a string input from the user.
     *
     * @param prompt The message to display to the user
     * @return The entered string
     */
    public static String stringInput(String prompt) {
        return stringInput(prompt, null, null);
    }

    /**
     * Reads and validates a string input against a regex pattern.
     *
     * @param prompt The message to display to the user
     * @param regexPattern The pattern to validate against
     * @param regexMessage The error message to display if validation fails
     * @return The validated string input
     */
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

    /**
     * Reads and validates a password input from the user.
     *
     * @param prompt The message to display to the user
     * @return The validated password
     */
    public static String passwordInput(String prompt) {
        String psswrd;
        while (true) {
            System.out.print(prompt);
            psswrd = scanner.nextLine();
            if (!isValidPassword(psswrd)) {
                System.out.println("Password shall contain at least one digit, one uppercase letter, and a minimum length of 6 characters");
            } else {
                break;
            }
        }
        return new String(psswrd);
    }

    /**
     * File path for the database
     */
    private static final String RECORDS_FILE = "Assignment #2\\App\\budgetapp\\src\\main\\java\\com\\budgetapp\\database\\database.json";

    /**
     * In-memory storage for records
     */
    private static final HashMap<String, Records> records = new HashMap<>();

    /**
     * Gson instance for JSON operations
     */
    private static final Gson gson = GsonTool.getGson();

    /**
     * Loads all records from the database file into memory.
     *
     * @return HashMap containing all records
     */
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

    /**
     * Saves the current records from memory to the database file.
     */
    private static void saveRecordsToFile() {
        try (Writer writer = new FileWriter(RECORDS_FILE)) {
            gson.toJson(records.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a specific field in a record.
     *
     * @param id The record identifier
     * @param fieldName The name of the field to update
     * @param newValue The new value for the field
     * @return true if update successful, false otherwise
     */
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

    /**
     * Inserts a new record into the database.
     *
     * @param rec The record to insert
     * @return true if insertion successful, false otherwise
     */
    public static boolean insertRecord(Records rec) {
        if (rec.id == null || records.containsKey(rec.id)) {
            return false;
        }
        records.put(rec.id, rec);
        saveRecordsToFile();
        System.out.println("The records file has been updated ");
        return true;
    }

    /**
     * Deletes a record from the database.
     *
     * @param id The identifier of the record to delete
     * @return true if deletion successful, false otherwise
     */
    public static boolean deleteRecord(String id) {
        if (records.containsKey(id)) {
            records.remove(id);
            saveRecordsToFile();
            return true;
        }
        System.out.println("record not found");
        return false;
    }

    /**
     * Retrieves a record by its identifier.
     *
     * @param id The record identifier
     * @return The found record or null if not found
     */
    public static Records getRecordById(String id) {
        return records.get(id);
    }

    /**
     * Closes the scanner resource.
     */
    public static void closeScanner() {
        scanner.close();
    }

    /**
     * Retrieves a user record and validates budget requirements.
     *
     * @param UUID The user's unique identifier
     * @param requiresBudget Whether to check for existing budget
     * @return The user record if found and valid, null otherwise
     */
    public static Records getUserRecord(String UUID, boolean requiresBudget) {
        Records userRecord = Methods.getRecordById(UUID);
        if (userRecord == null) {
            System.out.println("User record not found for UUID: " + UUID);
            return null;
        }

        if (requiresBudget) {
            if (userRecord.budget == null || userRecord.budget.isEmpty()) {
                System.out.println("Budget list for UUID: " + UUID + " is empty");
                return null;
            }
        }
        return userRecord;
    }
}
