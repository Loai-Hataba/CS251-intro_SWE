import java.util.*;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(10);
        Scanner scanner = new Scanner(System.in);

        parkingLot.displayGrid();

        while (true) {
            // Main menu options
            System.out.println("" +
                    "\n1. Park Vehicle" +
                    "\n2. Remove Vehicle" +
                    "\n3. Show Parking Status" +
                    "\n4. Reserve Slot" +
                    "\n5. View Parking History" +
                    "\n6. Admin Mode" +
                    "\n7. Search Vehicle" +
                    "\n8. Change Parking Rates" +
                    "\n9. View Statistics" +
                    "\n10. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter license plate: ");
                    String plate = scanner.nextLine();
                    System.out.print("VIP Slot? (yes/no): ");
                    boolean isVIP = scanner.nextLine().equalsIgnoreCase("yes");
                    System.out.print("Vehicle type (car/motorcycle/truck): ");
                    String vehicleType = scanner.nextLine().toLowerCase();
                    parkingLot.parkVehicle(plate, isVIP, vehicleType);
                    parkingLot.displayGrid();
                    break;
                case 2:
                    System.out.print("Enter license plate to remove: ");
                    plate = scanner.nextLine();
                    parkingLot.removeVehicle(plate);
                    parkingLot.displayGrid();
                    break;
                case 3:
                    parkingLot.displayGrid();
                    break;
                case 4:
                    System.out.print("Enter slot number to reserve: ");
                    int slotNum = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter reservation duration (hours): ");
                    int hours = scanner.nextInt();
                    scanner.nextLine();
                    parkingLot.reserveSlot(slotNum, hours);
                    break;
                case 5:
                    parkingLot.displayParkingHistory();
                    break;
                case 6:
                    // Simple password protection for admin mode
                    System.out.print("Enter admin password: ");
                    String password = scanner.nextLine();
                    if (password.equals("admin123")) {
                        parkingLot.adminMode();
                    } else {
                        System.out.println("Incorrect password!");
                    }
                    break;
                case 7:
                    System.out.print("Enter license plate to search: ");
                    plate = scanner.nextLine();
                    parkingLot.searchVehicle(plate);
                    break;
                case 8:
                    // Password protection for changing rates
                    System.out.print("Enter admin password: ");
                    password = scanner.nextLine();
                    if (password.equals("admin123")) {
                        System.out.print("Enter new regular rate: ");
                        double regularRate = scanner.nextDouble();
                        System.out.print("Enter new VIP rate: ");
                        double vipRate = scanner.nextDouble();
                        scanner.nextLine();
                        ParkingFeeCalc.updateRates(regularRate, vipRate);
                        System.out.println("Rates updated successfully!");
                    } else {
                        System.out.println("Incorrect password!");
                    }
                    break;
                case 9:
                    parkingLot.displayStatistics();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}