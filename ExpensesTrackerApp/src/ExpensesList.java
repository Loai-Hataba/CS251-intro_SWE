
import java.time.LocalDate;
import java.util.*;
//! This class represents the list of expenses that we will use 

public class ExpensesList {

    //Attributes:
    private final Scanner input = new Scanner(System.in);
    private final ArrayList<Expense> expenses = new ArrayList<>();

    //!Methods :
    //Method for displaying the list of expenses : 
    public void displayExpenses() {
        // adding a check if the list is empty 
        if (expenses.isEmpty()) {
            System.out.println("\n\n        Expenses list is Empty start adding some !!\n\n");
        } else {
            // for each loop to iterate over the list of expenses 
            int index = 1;
            System.out.println("=======================================================================================");
            for (final Expense e : expenses) {
                // Display them as cards
                System.out.println(" --------------------------------------------------------------------------");
                System.out.printf("| %-20s: %-50s |\n", "Expense number : ", index++);
                System.out.printf("| %-20s: %-50s |\n", "Title", e.getTitle());
                System.out.printf("| %-20s: %-50s |\n", "Category", e.getCategoryAsString());
                System.out.printf("| %-20s: %-50.2f |\n", "Amount", e.getAmount());
                System.out.printf("| %-20s: %-50s |\n", "Date", e.getDateAsString());
                System.out.println(" --------------------------------------------------------------------------\n");
            }
            System.out.println("=======================================================================================");
        }
    }

    //Method for getting a valid int input 
    private int getValidInt(final String msg, final String errorMsg) {
        int valid = 0;
        while (true) {
            try {
                System.out.println(msg);
                valid = input.nextInt();
                if (valid >= 0) {
                    break;
                } else {
                    System.out.println(errorMsg);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error : Invalid Integer input please enter a valid one \n");
                input.next();
            }
        }
        return valid;
    }

    //Method for getting a valid int input 
    private int getValidInt(final String msg, final String errorMsg, final int start, final int end) {
        int valid = 0;
        while (true) {
            try {
                System.out.println(msg);
                valid = input.nextInt();
                if (valid >= start && valid <= end) {
                    break;
                } else {
                    System.out.println(errorMsg);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error : Invalid Integer input please enter a valid one \n");
                input.next();
            }
        }
        return valid;
    }

    //! Method overLoading for getting a valid double input 
    private double getValidDouble(final String inputMsg, final String errorMsg) {
        double valid = 0;
        while (true) {
            try {
                System.out.println(inputMsg);
                valid = input.nextDouble();
                if (valid >= 0) {
                    break;
                } else {
                    System.out.println(errorMsg);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error : Invalid Double input please enter a valid one \n");
                input.next();
            }
        }
        return valid;

    }

    // Method for reading the category from user : 
    private Categories readCategories() {
        // var to store the category 
        final Categories currCategory;
        // Display the categories to the user : 
        System.out.println("Current Categories : ");
        for (final Categories c : Categories.values()) {
            System.out.println((c.ordinal() + 1) + "- " + c.name());
        }
        // Create a Scanner object to read the user input
        // Read the user input
        int index = 0;
        while (true) {
            index = getValidInt("Choose a category ( Write the index ) :", "Error : Invalid Index ! \n");
            if (index >= 1 && index <= Categories.values().length) {
                break;
            }
        }
        // Assign the category to the variable
        currCategory = Categories.values()[index - 1];
        // return the category 
        return currCategory;

    }

    //Method for reading the user date : 
    private LocalDate readDate() {
        LocalDate date = LocalDate.now(); // the default date is the current date
        while (true) {
            // make the user choose between the current date and a specific date 
            System.out.println("Do you want to add a specific date or use the current date  ?  ");
            int choice = getValidInt("1 - current date \n2 - specific date ", "Error : Invalid choice ! \n", 1, 2);
            if (choice == 2) {
                // read the year : 
                int year = getValidInt("Enter the year : ", "Error : It is a Year that have not came yet ! \n", 1970, date.getYear());
                // read the month : 
                int month = getValidInt("Enter the month ( 1 -> 12 ): ", "Error : Invalid month ( 1 -> 12 ) ! \n", 1, 12);
                // read the day                 
                LocalDate tmp = LocalDate.of(year, month, 1);
                // get the max days of the month for verification
                final int maxMonthDays = tmp.lengthOfMonth();
                int day = getValidInt("Enter the day ( 1 -> " + maxMonthDays + " ) ", "Error : Invalid day ( 1 - 31 ) ! \n", 1, maxMonthDays);
                date = LocalDate.of(year, month, day);
                // Validate the date : 
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Error : It is a date that have not came yet  ! ");
                } else {
                    break;// the date is valid

                }
            } else {
                break;  // the user choose the current date
            }
        }
        return date; // return the modified date
    }

    //Method for adding a new expense to the list :
    public void addExpense() {
        // Todo : add option to add  from file 
        //creating a Scanner object to read the user input
        System.out.println("            ( Adding a new expense )   ");
        System.out.println("\nEnter the title of the expense: ");
        input.nextLine();  // Clear the buffer
        final String currTitle = input.nextLine();
        //read the amount :
        final double currAmount = getValidDouble("Enter the amount of the expense : ", "Error : The amount should be positive ! \n");

        final Categories currCategory = readCategories();

        // read the date  :
        final LocalDate currDate = readDate();
        // Assigning the  values to an expense object 
        final Expense newExpense = new Expense(currTitle, currAmount, currCategory, currDate);
        // Adding the expense to the list
        expenses.add(newExpense);
        System.out.println("The expense has been added successfully ! ");

    }

    //Method for removing an expense from the list : 
    public void removeExpense() {
        // Check if the list is empty 
        if (expenses.isEmpty()) {
            System.out.println("\n\n          The list of expenses is empty ! \n\n");
            return;
        }
        System.out.println("              ( Removing an expense )   ");
        // Display the list 
        displayExpenses();
        // Ask the user to choose an expense to remove
        int index = 0;
        while (true) {
            index = getValidInt("Choose an expense to remove ( Write the index ) : ", "Error : Invalid Index ! \n");
            if (index >= 1 && index <= expenses.size()) {
                break;
            }
        }

        // Remove the expense from the list 
        expenses.remove(index - 1);
        System.out.println("The expense has been removed successfully ! ");
    }

    //Method for sorting the expenses list :
    public void sortExpenses() {
        // check if the list is empty 
        if (expenses.isEmpty()) {
            System.out.println("\n\n          The list of expenses is empty ! \n\n");
            return;
        }
        // Sorting the list of expenses : 
        System.out.println("Do you want to sort the list by : ");
        System.out.println("1- Amount ");
        System.out.println("2- Date ");
        System.out.println("3- Category ");
        //read the user choice  :
        int choice = getValidInt("Your Choice ( 1 -> 3 )", "Error : choice must be between ( 1 / 3 ) as Integer", 1, 3);
        //make the user choose between ascending and descending order
        System.out.println("Do you want to sort in : ");
        System.out.println("1- Ascending order ");
        System.out.println("2- Descending order ");
        int option = getValidInt("Your choice (1 -> 2 )", "Error : choice must be between ( 1 / 2 ) as Integer", 1, 2);
        switch (choice) {
            case 1 -> {
                // sort by amount
                sortByAmount(option == 1);
            }
            case 2 -> {
                // sort by date 
                sortByDate(option == 1);
            }
            default ->
                throw new AssertionError();
        }
    }

    //! method for sorting by amount :
    private void sortByAmount(final boolean ascending) {
        // we will use the insertion sort algorithm:
        double key;
        int j;

        for (int i = 1; i < expenses.size(); i++) {
            // if the user choose to sort in ascending order
            if (ascending) {
                // 
                key = expenses.get(i).getAmount();
                Expense tmp = expenses.get(i);
                j = i - 1;
                while (j >= 0 && key < expenses.get(j).getAmount()) {
                    expenses.set(j + 1, expenses.get(j));
                    --j;
                }
                expenses.set(j + 1, tmp);
            } // if the user choose to sort in descending order
            else {
                // 
                key = expenses.get(i).getAmount();
                Expense tmp = expenses.get(i);
                j = i - 1;
                while (j >= 0 && key > expenses.get(j).getAmount()) {
                    expenses.set(j + 1, expenses.get(j));
                    --j;
                }
                expenses.set(j + 1, tmp);
            }
        }
        if (ascending) {
            System.out.println("The list has been sorted in ascending order by amount ! "); 
        }else {
            System.out.println("The list has been sorted in descending order by amount ! ");
        }
    }

    //Method for sorting the expenses list by date  :
    private void sortByDate(boolean ascending) {
        // we will use the selection sort algorithm : 
        int minIdx;
        for (int i = 0; i < expenses.size() - 1; i++) {
            minIdx = i;
            for (int j = i; j < expenses.size(); j++) {
                if (ascending) {
                    if (expenses.get(j).getDate().isBefore(expenses.get(minIdx).getDate())) {
                        minIdx = j;
                    }
                } else {
                    if (expenses.get(j).getDate().isAfter(expenses.get(minIdx).getDate())) {
                        minIdx = j;
                    }
                }
            }
            Expense temp = expenses.get(minIdx);
            expenses.set(minIdx, expenses.get(i));
            expenses.set(i, temp);
        }
        if (ascending) {
            System.out.println("The list has been sorted in ascending order by date ! ");
        } else {
            System.out.println("The list has been sorted in descending order by date ! ");
        }

    }
}
