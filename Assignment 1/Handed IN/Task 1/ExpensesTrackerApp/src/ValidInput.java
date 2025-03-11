
import java.util.*;

// this class is for getting a valid input for  most data types
public class ValidInput {

    // vars : 
    // scanner to read the  user input 
    private final Scanner input = new Scanner(System.in);

    //!  methods : 
    // method to get a valid user int input 
    public int getValidInt(final String msg, final String errorMsg) {
        int valid = 0;
        while (true) {
            try {
                System.out.printf(msg);
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

    // overloading for the getValidInt methods adding start and an end 
    public int getValidInt(final String msg, final String errorMsg, final int start, final int end) {
        int valid = 0;
        while (true) {
            try {
                System.out.printf(msg);
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

    // Method for getting a valid double input 
    public double getValidDouble(final String inputMsg, final String errorMsg) {
        double valid = 0;
        while (true) {
            try {
                System.out.printf(inputMsg);
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

    // method for getting an string input : 
    public String getValidString(final String inputMsg) {
        String valid;
        System.out.printf(inputMsg);
        if (input.hasNextLine()) {
            // clear the buffer if there is a leftover 
            input.nextLine();
        }
        valid = input.nextLine();
        return valid;
    }

}
