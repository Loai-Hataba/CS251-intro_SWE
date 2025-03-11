
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

///This class is a blue print for storing the expense details 
public class Expense {

    //Attributes :
    private final double amount;	// Amount of the expense 
    private final String title;  // Title of the expense
    private final Categories category; // the kind of expense 
    private final LocalDate date; // Date of the expense

    /////////////////////////////////////////////////////////////////////////////////////////////
                            /// Methods : 

//Constructor :
    public Expense(String title, double amount, Categories category, LocalDate date) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters : 
    //getter for the title 
    public String getTitle() {
        return this.title;
    }

    //getter for the amount 
    public double getAmount() {
        return this.amount;
    }

    //getter for the category ==>> as string 
    public String getCategoryAsString() {
        return this.category.toString();
    }

    //getter for the date ==>> as string 
    public String getDateAsString() {
        //Creating a date time formatter object   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // formatting the date using formatting.format method
        return this.date.format(formatter);
    }

    // getter for the date as LocalDate object
    public LocalDate getDate() {
        return this.date;
    }

}
