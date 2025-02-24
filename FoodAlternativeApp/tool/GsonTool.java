package tool;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import food.FoodItem;

public class GsonTool {
    private static final Gson gson = new Gson(); // Create Gson instance

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
}

