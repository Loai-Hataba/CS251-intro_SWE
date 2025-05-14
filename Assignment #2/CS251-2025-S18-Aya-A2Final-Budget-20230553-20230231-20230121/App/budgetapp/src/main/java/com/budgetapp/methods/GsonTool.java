package com.budgetapp.methods;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Utility class that provides a singleton instance of Gson for JSON operations.
 * This class ensures consistent JSON formatting across the application.
 */
public class GsonTool {

    /**
     * The single instance of Gson configured with pretty printing. This
     * instance is thread-safe and can be shared across the application.
     */
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Private constructor to prevent instantiation. This class should only be
     * used through its static method.
     */
    private GsonTool() {
    }

    /**
     * Returns the singleton instance of Gson.
     *
     * @return A configured Gson instance with pretty printing enabled
     */
    public static Gson getGson() {
        return gson;
    }
}
