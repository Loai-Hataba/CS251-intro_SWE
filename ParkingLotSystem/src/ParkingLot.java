import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.Duration;
import java.time.LocalDateTime;
class ParkingLot {
    private List<ParkingSlot> slots;
    private List<String> parkingHistory;

    public ParkingLot(int capacity) {
        slots = new ArrayList<>();
        parkingHistory = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            slots.add(new ParkingSlot(i, i <= 2)); // First 2 slots are VIP
        }
    }

    public boolean parkVehicle(String licensePlate, boolean isVIP) {
        Optional<ParkingSlot> availableSlot = slots.stream()
                .filter(slot -> !slot.isOccupied() && !slot.isReserved() && slot.isVIP() == isVIP)
                .findFirst();

        if (availableSlot.isPresent()) {
            availableSlot.get().parkVehicle(new Vehicle(licensePlate,isVIP));
            System.out.println("Vehicle parked in slot " + availableSlot.get().getSlotNumber());
            return true;
        } else {
            System.out.println("No available " + (isVIP ? "VIP " : "") + "slots!");
            return false;
        }
    }

    public boolean removeVehicle(String licensePlate) {
        Optional<ParkingSlot> occupiedSlot = slots.stream()
                .filter(slot -> slot.isOccupied() && slot.getVehicle().getLicensePlate().equals(licensePlate))
                .findFirst();

        if (occupiedSlot.isPresent()) {
            Vehicle vehicle = occupiedSlot.get().getVehicle();
            long secsParked = Duration.between(vehicle.getEntryTime(), LocalDateTime.now()).toSeconds();
            double fee = ParkingFeeCalc.calculateFee(vehicle.getEntryTime(), vehicle.isVIP());
            occupiedSlot.get().removeVehicle();
            String record = "Vehicle " + licensePlate + " removed. Parked for: " + secsParked + " seconds. Fee: $" + fee;
            parkingHistory.add(record);
            System.out.println(record);
            displayGrid();
            return true;
        } else {
            System.out.println("Vehicle not found!");
            return false;
        }
    }

    public void displayGrid() {
        long availableSlots = slots.stream().filter(slot -> !slot.isOccupied()).count();
        System.out.println("\nParking Lot Status:");
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).isOccupied()) {
                System.out.print("[X] ");
            }
            else if(slots.get(i).isVIP()){
                    System.out.print("[V] ");
                }
            else if(slots.get(i).isReserved()){
                System.out.print("[R] ");
            }
            else {
                System.out.print("[ ] ");
            }
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println("\nAvailable slots: " + availableSlots);
    }

    public void reserveSlot(int slotNumber) {
        if (slotNumber > 0 && slotNumber <= slots.size()) {
            slots.get(slotNumber - 1).reserveSlot();
            System.out.println("Slot " + slotNumber + " reserved.");
        } else {
            System.out.println("Invalid slot number!");
        }
    }

    public void displayParkingHistory() {
        System.out.println("\nParking History:");
        for (String record : parkingHistory) {
            System.out.println(record);
        }
    }

    public void adminMode() {
        System.out.println("Admin mode activated: Displaying all slots");
        slots.forEach(slot -> System.out.println("Slot " + slot.getSlotNumber() + " | VIP: " + slot.isVIP() + " | Occupied: " + slot.isOccupied() + " | Reserved: " + slot.isReserved()));
    }
}