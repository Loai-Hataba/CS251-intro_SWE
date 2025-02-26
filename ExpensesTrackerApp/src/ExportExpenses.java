
import java.io.*;
import java.util.ArrayList;

public class ExportExpenses {

    // vars : 
    private final ArrayList<String> content = new ArrayList<>();
    private final static ValidInput validInput = new ValidInput();

    //Methods : 
    // method for getting the file name and path : 
    private String getFileName() {
        // make the user choose between the current directory or a specific one 
        while (true) {
            String msg = "Do you want the file be in the current directory or a specific one ?\n1)Yes\n2)No";
            int choice = validInput.getValidInt(msg, "Error : Invalid Choice ", 1, 2);
            // if the user's choice  is  the current dir 
            String name;
            if (choice == 1) {
                name = validInput.getValidString("Enter the file name without any file format ");
            } // if the user's choice  is  a specific dir 
            else {
                String dirPath = validInput.getValidString("Please enter the directory path : ");
                name = validInput.getValidString("Enter the file name without any file format ");
                if (name == "") {
                    name = "First";
                }
                System.out.println(File.separator);
                name = dirPath + File.separator + name;
                dirPath = dirPath.trim(); // for removing the spaces 
                if (!new File(dirPath).exists()) {
                    System.out.println("Error: Directory does not exist.");
                    continue;

                }
            }
            return name;
        }
    }

    public void addContent(final String Line) {
        // function for adding each line 
        content.add(Line);
    }

    // method for start exporting : 
    public void export() throws IOException {
        System.out.println("              ( Saving an expense )   ");
        // 1- read the file path 
        final String name = getFileName();
        //2- creating a file object 
        final File file = new File(name);
        // Debugging
        System.out.println("Attempting to create file at: " + name);
        // 3- creating the file  (empty file)
        if (!file.exists()) {
            boolean created = file.createNewFile();
            if (!created) {
                System.out.println("Error: Failed to create file.");
                return;
            }
        } else {
            System.out.println("File already exists. Overwriting...");
        }
        try (FileWriter writer = new FileWriter(file)) {
            //5- set the data to the file
            for (final String line : content) {
                writer.write(line);
            }
        }
        System.out.println("Export successful: " + name);
    }

}
