package food;
import java.util.*;

public class FoodItem {
    private String name;
    private String unit;
    private int calories;
    // store alternative foods and their conversion ratees
    private HashMap<String, Double> alternatives;

    public FoodItem(){}

    public FoodItem(String name, String unit, int calories, HashMap<String, Double> alternatives)
    {
        this.name = name;
        this.unit = unit;
        this.calories = calories;
        this.alternatives = alternatives;
    }

    public String getName(){ return name;}
    public int getCalorie(){ return calories;}
    public HashMap<String, Double> getAlternatives(){
        HashMap<String, Double> alts = new HashMap<>();
        for (var alt : alternatives.entrySet()){
            alts.put(alt.getKey(), alt.getValue());
        }
        return alts;
    }
    public List<Double> getFactors(){
        List<Double> factors = new ArrayList<>();
        for (var alt : alternatives.entrySet()){
            factors.add(alt.getValue());
        }
        return factors;
    }
    public List<String> getFoods(){
        List<String> factors = new ArrayList<>();
        for (var alt : alternatives.entrySet()){
            factors.add(alt.getKey());
        }
        return factors;
    }
    

    public String toString() {
        String result = "Food: " + name + ", calories:" + calories + " per 100 " + unit + ", Alternatives=[";
    
        for (var entry : alternatives.entrySet()) {
            result += entry.getKey() + ": " + entry.getValue() + ", ";
        }
    
        if (!alternatives.isEmpty()) {
            result = result.substring(0, result.length() - 2);
        }
    
        return result + "]";
    }

}
