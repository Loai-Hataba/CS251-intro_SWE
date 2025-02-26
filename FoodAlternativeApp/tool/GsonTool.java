package tool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Iterator;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import food.FoodItem;

public class GsonTool {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Create Gson instance

    // Load food from JSON
    public static List<FoodItem> loadFood(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type foodListType = new TypeToken<List<FoodItem>>() {}.getType();
            return gson.fromJson(reader, foodListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveFood(String filePath, List<FoodItem> foodList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(foodList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean deleteFood(String filePath, List<FoodItem> foodlist, String foodName) {
        if (foodlist == null || foodlist.isEmpty()) {
            System.out.println("Failed to load food data.");
            return false;
        }
        boolean found = false;
        Iterator<FoodItem> iterator = foodlist.iterator();
        while (iterator.hasNext()) {
            FoodItem item = iterator.next();
            if (item.getName().equalsIgnoreCase(foodName)) {
                iterator.remove();
                found = true;
                break;
            }
        }
        saveFood(filePath, foodlist);
        return found;
    }
}

