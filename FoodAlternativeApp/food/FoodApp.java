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

    public static void printBanner(){
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
    } 

    public static int optionsMenu(Scanner scanner)
    {
        System.out.println("\n                                      Welcome choose an option:\n");
        System.out.println("1)Find Alternative Food\n"
                          +"2)Add new Food\n"
                          +"3)Delete Food\n"
                          +"4)Info\n"
                          +"5)Exit\n");
        int ans;
        while(true){
            System.out.print("Choice: ");
            if (scanner.hasNextInt()){
                ans = scanner.nextInt();
                if (ans > 0 && ans < 6) break;
                else System.out.print("Please enter a number from (1-5) !\n");
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); 
            }
        }
        return ans;
    }

    public static void foodMenu(Scanner scanner, List<FoodItem> foodList){
        int count = 1;
        System.out.println();
        for (var food : foodList)
        {
            System.out.println(count++ + ") " + food.getName());
        }
        System.out.println();

            // Handling Input
        int foodNum;
        double foodWeight;
        while(true){
            System.out.print("Food Number: ");
            if (scanner.hasNextInt()){
                foodNum = scanner.nextInt();
                if (foodNum > 0 && foodNum < count) break;
                else System.out.print("Please enter a number from (1-" + (count-1) + ") !\n");
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
                scanner.next();
            }
        }

        //Create Food Object
        FoodItem item = foodList.get(foodNum - 1);
        System.out.println();
        System.out.println(item);
        HashMap<String, Double> alternatives = item.getAlternatives();
        System.out.println("\nAlternatives:");
        int counter = 1;
        for (var alt : alternatives.entrySet()){
            System.out.println(counter++ + ") " + alt.getKey() + ", factor: " + alt.getValue());
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

    }

    public static void deleteFood(Scanner scanner, List<FoodItem> foodList, String jsonPath)
    {
        scanner.nextLine();
        System.out.print("\nDelete Food:\n"
                            +"Food Name: ");
        String ans = scanner.nextLine();
        boolean found = GsonTool.deleteFood(jsonPath, foodList, ans);
        if (found) System.out.println(ans + " was deleted successfully!\n");
        else System.out.println("Couldn't find " + ans + " anywhere :(\n");
    }

    public static void addFood(Scanner scanner, List<FoodItem> foodList, String jsonPath)
    {
        scanner.nextLine();
        System.out.print("\nAdd Food: ");
        String foodName = scanner.nextLine();
        int calories;
        HashMap<String, Double> alternatives = new HashMap<>();
        while(true){
            System.out.print("\nCalories per 100g: ");
            if (scanner.hasNextInt()){
                calories = scanner.nextInt();
                if (calories < 0)
                {
                    System.out.println("Please Enter a positive number!");
                } else break;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); 
            }
        }
        int alts;
        System.out.println("\nAlternatives:");
        System.out.print("How many Alternatives: ");
        //Number of Alternatives
        while (true){
            if(scanner.hasNextInt()){
                alts = scanner.nextInt();
                if (alts < 1)
                {
                    System.out.println("Please Enter a Positive Number!");
                } else break;
            } else{
                System.out.println("Please Enter a valid Number!");
                scanner.next();
            }
        }
        // Enter Alternativess!
        for (int i = 0; i < alts; i++){
            scanner.nextLine();
            System.out.print("\nFood Name: ");
            String altname = scanner.nextLine();
            Double factor;
            while(true){
                System.out.print("Conversion Factor: ");
                if (scanner.hasNextDouble()){
                    factor = scanner.nextDouble();
                    if (factor < 0)
                    {
                        System.out.println("Please Enter a positive number!");
                    } else break;
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); 
                }
            }
            //add food to alternative liste
            alternatives.put(altname, factor);   
        }
        FoodItem item = new FoodItem(foodName, "grams", calories, alternatives);
        foodList.add(item);
        GsonTool.saveFood(jsonPath, foodList);
        System.out.println("Food Added!\n");

    }
    
    public static int continueApp(Scanner scanner){
        char ans;
        while (true) {
            System.out.print("Do you want to continue? (Y/N) ");
            String input = scanner.next();  
            if (input.length() == 1) {
                ans = input.charAt(0);
                if (Character.isLetter(ans) && Character.toLowerCase(ans) == 'y') {
                    //Clear Terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    return 1;
                }
                else if (Character.isLetter(ans) && Character.toLowerCase(ans) == 'n') {
                    System.out.println("Goodbye!!");
                    return 0;
                }
                else {
                    System.out.println("Please enter a valid letter!");
                }
            } else {
                System.out.println("Invalid input. Enter only one character.");
            }
        }
    }

    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        // Load food from JSON
        String jsonPath = "food/foodDictionary.json";
        List<FoodItem> foodList = GsonTool.loadFood(jsonPath);
        while (true){
            printBanner();
            int menu = optionsMenu(scanner);
            switch(menu)
            {
                // Alternative Food
                case 1:
                    foodMenu(scanner, foodList);
                    int ans = continueApp(scanner);
                    if (ans == 0){
                        scanner.close();
                        System.exit(0);
                    }
                    break;
                    
                //Add new Food
                case 2:
                    addFood(scanner, foodList, jsonPath);
                    int ans2 = continueApp(scanner);
                    if (ans2 == 0){
                        scanner.close();
                        System.exit(0);
                    }
                    break;

                // Delete Food
                case 3:
                    deleteFood(scanner, foodList, jsonPath);
                    int ans3 = continueApp(scanner);
                    if (ans3 == 0){
                        scanner.close();
                        System.exit(0);
                    }
                    break;
                
                case 4:
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\n\n            +-------------------------------------------------------+");
                    System.out.println("            ||" + GREEN + " _____       __        _____            _            " + RESET + "||");
                    System.out.println("            ||" + GREEN + "|_   _|     / _|      /  __ \\          | |           " + RESET + "||");
                    System.out.println("            ||" + GREEN + "  | | _ __ | |_ ___   | /  \\/ ___ _ __ | |_ _ __ ___ " + RESET + "||");
                    System.out.println("            ||" + GREEN + "  | || '_ \\|  _/ _ \\  | |    / _ \\ '_ \\| __| '__/ _ \\" + RESET + "||");
                    System.out.println("            ||" + GREEN + " _| || | | | || (_) | | \\__/\\  __/ | | | |_| | |  __/" + RESET + "||");
                    System.out.println("            ||" + GREEN + " \\___/_| |_|_| \\___/   \\____/\\___|_| |_|\\__|_|  \\___|" + RESET + "||");
                    System.out.println("            +-------------------------------------------------------+\n\n\n\n\n");
                    System.out.println(BOLD + "     This Program was made by " + UNDERLINE + "Loai Hataba" + RESET);
                    System.out.println("* ==============================================");
                    System.out.println("*  Food Alternative Dictionary");
                    System.out.println("* ==============================================");
                    System.out.println("*  This application helps users find and compare ");
                    System.out.println("*  food alternatives based on conversion factors.");
                    System.out.println("*  ");
                    System.out.println("\n*  Features:");
                    System.out.println("*  - View food details (unit, calories, alternatives)");
                    System.out.println("*  - Add new foods with alternative conversions");
                    System.out.println("*  - Delete foods from the database in real time");
                    System.out.println("*  - Interactive menu with error handling");
                    System.out.println("*  - Data stored in JSON for persistence");
                    System.out.println("*  ");
                    System.out.println("*  Technologies Used:");
                    System.out.println("*  - Java");
                    System.out.println("*  - Gson (for JSON handling)");
                    System.out.println("*  - HashMaps & Lists for data management");
                    System.out.println("*  ");
                    System.out.println("* ==============================================");
                    int ans4 = continueApp(scanner);
                    if (ans4 == 0){
                        scanner.close();
                        System.exit(0);
                    }
                    break;


                case 5:
                    System.out.println("\nGoodbye!!");
                    scanner.close();
                    System.exit(0);
                    break;
                    

            }
        }
    }
    
}
