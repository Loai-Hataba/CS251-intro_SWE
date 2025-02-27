import java.util.*;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(10);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========= Parking Lot System =========");
            parkingLot.displaySlots();
            System.out.println("1. Park Vehicle\n2. Remove Vehicle\n3. Show Available Slots\n4. Show Parking History\n5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter license plate: ");
                    String plate = scanner.nextLine();
                    System.out.print("VIP slot (y/n)? ");
                    boolean isVIP = scanner.nextLine().equalsIgnoreCase("y");
                    parkingLot.parkVehicle(plate, isVIP);
                    break;
                case 2:
                    System.out.print("Enter license plate to remove: ");
                    plate = scanner.nextLine();
                    parkingLot.removeVehicle(plate);
                    break;
                case 3:
                    parkingLot.displayAvailableSlots();
                    break;
                case 4:
                    parkingLot.showParkingHistory();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}