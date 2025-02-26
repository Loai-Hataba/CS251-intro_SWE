
public class App {

    private final static ValidInput validInput = new ValidInput();

    public static void main(String[] args) throws Exception {
        System.out.println("            ***Welcome to the Expenses Manager App***");
        System.out.println("            =========================================\n");
        // Create an instance of the expenses list 
        final ExpensesList myExpenses = new ExpensesList();
        //The app menu : 
        while (true) {
            System.out.println("\n                          Main Menu  ");
            System.out.println("                     ===================\n");
            // Main menu options : 
            // 1 Adding an expense : 
            System.out.println("1. Add a new expense ");
            // 2 removing an expense :
            System.out.println("2. Remove an expense ");
            // 3 Display the expenses list :
            System.out.println("3. Display the expenses list ");
            // 4 Sort the expenses list : 
            System.out.println("4. Sort the expenses list ");
            // 5 Export the expenses list to a file :
            System.out.println("5. Export the expenses list to a file ");
            // 6 Exit the app :
            System.out.println("6. Exit the app ");
            //read the user choice  :

            final int choice = validInput.getValidInt("\nYour Choice is ( 1 -> 6 ) : ", "Error : Invalid Choice !!", 1, 6);
            switch (choice) {
                case 1 ->
                    myExpenses.addExpense();
                case 2 ->
                    myExpenses.removeExpense();
                case 3 ->
                    myExpenses.displayExpenses();
                case 4 ->
                    myExpenses.sortExpenses();
                case 5 ->
                    myExpenses.exportExpenses();
                case 6 -> {
                    final int ch = validInput.getValidInt("Do you want to saving before closing ?\n1)Yes\n2)No ", "Error : Invalid Choice !!", 1, 2);
                    if (ch == 1) {
                        myExpenses.exportExpenses();
                    }
                    System.out.println("Terminating the program :(");
                    return;
                }
                default ->
                    throw new AssertionError();
            }

        }

    }
}
