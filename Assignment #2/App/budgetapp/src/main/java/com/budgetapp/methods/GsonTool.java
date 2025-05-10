package com.budgetapp.methods;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTool {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private GsonTool() {}

    public static Gson getGson() {
        return gson;
    }
}
