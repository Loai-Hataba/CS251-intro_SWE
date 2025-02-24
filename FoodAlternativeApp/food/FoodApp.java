package food;

import tool.GsonTool;
import java.util.*;


public class FoodApp{
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\033[1m";
    public static final String UNDERLINE = "\033[4m";

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        // Load food from JSON
        String jsonPath = "food/foodDictionary.json";
        List<FoodItem> foodList = GsonTool.loadFood(jsonPath);
    
        while(true){
            //Clean Terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("        											    (  )   (   )  )");
            System.out.println("        											     ) (   )  (  (");
            System.out.println("        											     ( )  (    ) )");
            System.out.println(RED + "        ..............................................................................." + RESET + "		     _____________");
            System.out.println(RED + "        :" + RESET + YELLOW + " ______              _            _ _                        _   _           " + RED + ":" + RESET + " 	    <_____________> ___");
            System.out.println(RED + "        :" + RESET + YELLOW + "|  ____|            | |     /\\   | | |                      | | (_)          " + RED + ":" + RESET + "		    |             |/ _ \\");
            System.out.println(RED + "        :" + RESET + YELLOW + "| |__ ___   ___   __| |    /  \\  | | |_ ___ _ __ _ __   __ _| |_ ___   _____ " + RED + ":" + RESET + "		    |               | | |");
            System.out.println(RED + "        :" + RESET + YELLOW + "|  __/ _ \\ / _ \\ / _` |   / /\\ \\ | | __/ _ \\ '__| '_ \\ / _` | __| \\ \\ / / _ \\" + RED + ":" + RESET + "		    |               |_| |");
            System.out.println(RED + "        :" + RESET + YELLOW + "| | | (_) | (_) | (_| |  / ____ \\| | ||  __/ |  | | | | (_| | |_| |\\ V /  __/" + RED + ":" + RESET + "		 ___|             |\\___/");
            System.out.println(RED + "        :" + RESET + YELLOW + "|_|  \\___/ \\___/ \\__,_| /_/    \\_\\_|\\__\\___|_|  |_| |_|\\__,_|\\__|_| \\_/ \\___|" + RED + ":" + RESET + "		/    \\___________/    \\");
            System.out.println(RED + "        ..............................................................................." + RESET + "		\\_____________________/");
            System.out.println();
            System.out.print("Choose Food:\n\n"
                                + BOLD + UNDERLINE + GREEN +  "Proteins:\n" + RESET
                                +"1)Chicken Breast\n"
                                +"2)Salmon\n"
                                +"3)Eggs\n"
                                +"4)Tofu\n"
                                +"5)Lentils\n"
                                +"6)Greek Yogurt\n"
                                +"7)Almonds\n"
                                +"8)Black Beans\n"
                                +"9)Cottage Cheese\n\n\n"
                                + BOLD + UNDERLINE + GREEN +  "Carbohydrates:\n" + RESET
                                +"10)Quinoa\n"
                                +"11)Brown Rice\n"
                                +"12)Oats\n"
                                +"13)Whole Wheat Bread\n"
                                +"14)Pasta\n\n\n"
                                + BOLD + UNDERLINE + GREEN +  "Vegetables and Fruits:\n" + RESET
                                +"15)Sweet Potatoes\n"
                                +"16)Bananas\n"
                                +"17)Chickpeas\n"
                                +"18)Potatoes\n"
                                +"19)Apples\n"
                                +"20)Lentils\n"
                                +"21)Spinach\n"
                                +"22)Carrots\n"
                                +"23)Broccoli\n"
                                +"24)Tomatoes\n"
                                +"25)Oranges\n"
                                +"26)Berries\n"
                                +"27)Mushrooms\n"
                                +"28)Bell Peppers\n"
                                +"29)Grapes\n"
                                +"30)Pineapple\n\n\n"
                                + BOLD + UNDERLINE + GREEN +  "Fats:\n" + RESET
                                +"31)Olive Oil\n"
                                +"32)Butter\n"
                                +"33)Coconut Oil\n"
                                +"34)Avocados\n"
                                +"35)Walnuts\n\n");

            // Handling Input
            int foodNum;
            double foodWeight;
            while(true){
                System.err.print("Food Number: ");
                if (scanner.hasNextInt()){
                    foodNum = scanner.nextInt();
                    if (foodNum > 0 && foodNum < 36) break;
                    else System.out.print("Please enter a number from (1-35) !\n");
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); 
                }
            }
            while (true) {
                System.out.print("Enter amount (grams): ");
                if (scanner.hasNextDouble()) {
                    foodWeight = scanner.nextDouble();
                    if (foodWeight > 0) break;
                    else System.out.println("Please enter a positive number.");
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // Clear invalid input
                }
            }

            //Create Food Object
            FoodItem item = foodList.get(foodNum - 1);
            System.out.println();
            System.out.println(item);
            HashMap<String, Double> alternatives = item.getAlternatives();
            System.out.println("\nAlternatives:");
            int count = 1;
            for (var alt : alternatives.entrySet()){
                System.out.println(count++ + ") " + alt.getKey() + ", factor: " + alt.getValue());
            }

            //Choose alternative
            int altNum;
            while (true){
                System.out.print("Choose Food: ");
                if (scanner.hasNextInt())
                {
                    altNum = scanner.nextInt();
                    if (altNum > 0 && altNum < count)break;
                    else System.out.println("Please a number from (1-" + (count - 1) + ")");
                }else{
                    System.out.println("Please enter a valid Number!");
                    scanner.next();
                }
            }
            //Conversion Factor
            Double altWeight = Math.round(((foodWeight) * (item.getFactors().get(altNum - 1))) * 100.0) / 100.0;
            System.out.println("\nAlternative Food: " + item.getFoods().get(altNum - 1) + ", " + altWeight + " grams\n");

            char ans;
            while (true) {
                System.out.print("Do you want to continue? (Y/N) ");
                String input = scanner.next();  
                if (input.length() == 1) {
                    ans = input.charAt(0);
                    if (Character.isLetter(ans) && (Character.toLowerCase(ans) == 'y' || Character.toLowerCase(ans) == 'n')) {
                        break;
                    } else {
                        System.out.println("Please enter a valid letter!");
                    }
                } else {
                    System.out.println("Invalid input. Enter only one character.");
                }
            }
            if (Character.toLowerCase(ans) == 'n') {
                System.out.println("Goodbye!!");
                break;
            }
            //Clear Terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        scanner.close();
    }
    
}

