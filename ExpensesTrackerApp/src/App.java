
import java.util.*;

public class App {

    private static final Scanner input = new Scanner(System.in);

    // function to get a valid input choice for indexes 
    public static int getValidInt(final int start, final int end, final String msg) {
        int valid = 0;
        // get the user input 
        while (true) {
            try {
                System.out.println(msg);
                valid = input.nextInt();
                if (valid >= start && valid <= end) {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                input.next();
            }
        }
        return valid;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("            ***Welcome to the Expenses Manager App***");
        System.out.println("            =========================================\n");
        // Create an instance of the expenses list 
        final ExpensesList myExpenses = new ExpensesList();
        // todo : add a chart for the expenses ( We're cooked )
        //todo : Export to File
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
            final int choice = getValidInt(1, 6, "\nYour Choice is ( 1 -> 6 ) : ");
            switch (choice) {
                case 1 ->
                    myExpenses.addExpense();
                case 2 ->
                    myExpenses.removeExpense();
                case 3 ->
                    myExpenses.displayExpenses();
                case 4 ->
                    myExpenses.sortExpenses();
                case 6 -> {
                    System.out.println("Terminating the program :(");
                    return;
                }
                default ->
                    throw new AssertionError();
            }

        }

    }
}
/*GitHub Copilot
Here are some features you can add to your Expenses Manager App to enhance its functionality:

Edit Expense:

Allow users to edit existing expenses. This can include changing the title, amount, category, or date of an expense.
Search Expenses:

Implement a search feature to allow users to find expenses by title, category, or date.
Sort Expenses:

Add functionality to sort expenses by date, amount, or category.
Filter Expenses:

Allow users to filter expenses by category or date range.
Export to File:

Provide an option to export the list of expenses to a CSV or text file for easy sharing and backup.
Import from File:

Allow users to import expenses from a CSV or text file.
Monthly Summary:

Generate a summary of expenses for a given month, showing total expenses, highest expense, and other statistics.
Budget Tracking:

Allow users to set a monthly budget and track their expenses against it. Notify users when they are close to or have exceeded their budget.
Recurring Expenses:

Add support for recurring expenses that automatically add themselves to the list at specified intervals (e.g., monthly bills).
Graphical Reports:

Generate graphical reports (e.g., pie charts, bar charts) to visualize expenses by category, month, etc.
User Authentication:

Implement user authentication to allow multiple users to manage their own expenses.
Expense Notes:

Allow users to add notes or descriptions to each expense for more detailed tracking.
Currency Conversion:

Add support for multiple currencies and automatic conversion based on current exchange rates.
Mobile App Integration:

Develop a mobile version of the app or integrate with existing mobile expense tracking apps.
Notifications:

Send notifications or reminders for upcoming expenses or when the user is close to their budget limit. */
