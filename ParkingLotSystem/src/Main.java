import java.util.*;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(10);
        Scanner scanner = new Scanner(System.in);

        parkingLot.displayGrid();

        while (true) {
            System.out.println("\n1. Park Vehicle\n2. Remove Vehicle\n3. Show Parking Status\n4. Reserve Slot\n5. View Parking History\n6. Admin Mode\n7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter license plate: ");
                    String plate = scanner.nextLine();
                    System.out.print("VIP Slot? (yes/no): ");
                    boolean isVIP = scanner.nextLine().equalsIgnoreCase("yes");
                    parkingLot.parkVehicle(plate, isVIP);
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
                    parkingLot.reserveSlot(slotNum);
                    break;
                case 5:
                    parkingLot.displayParkingHistory();
                    break;
                case 6:
                    parkingLot.adminMode();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}