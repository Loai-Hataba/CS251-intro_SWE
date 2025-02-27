import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
class ParkingLot {
    private List<ParkingSlot> slots;
    private List<String> history;

    public ParkingLot(int capacity) {
        slots = new ArrayList<>();
        history = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            slots.add(new ParkingSlot(i, i <= 2)); // First 2 slots are VIP
        }
    }

    public boolean parkVehicle(String licensePlate, boolean isVIP) {
        Optional<ParkingSlot> availableSlot = slots.stream()
                .filter(slot -> !slot.isOccupied() && slot.isVIP() == isVIP)
                .findFirst();

        if (availableSlot.isPresent()) {
            availableSlot.get().parkVehicle(new Vehicle(licensePlate, isVIP));
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
            double fee = ParkingFeeCalc.calculateFee(vehicle.getEntryTime(), vehicle.isVIP());
            occupiedSlot.get().removeVehicle();
            history.add("Vehicle " + licensePlate + " removed. Fee: $" + fee);
            System.out.println("Vehicle removed. Fee: $" + fee);
            return true;
        } else {
            System.out.println("Vehicle not found!");
            return false;
        }
    }

    public void displayAvailableSlots() {
        long count = slots.stream().filter(slot -> !slot.isOccupied()).count();
        System.out.println("Available slots: " + count);
    }

    public void showParkingHistory() {
        if (history.isEmpty()) {
            System.out.println("No history available.");
        } else {
            history.forEach(System.out::println);
        }
    }

    public void displaySlots() {
        System.out.println("\nParking Lot Layout:");
        for (int i = 0; i < slots.size(); i++) {
            ParkingSlot slot = slots.get(i);
            String status = slot.isOccupied() ? "[X]" : "[ ]";
            String type = slot.isVIP() ? "V" : " ";
            System.out.print("| " + type + status + " ");
            if ((i + 1) % 5 == 0) {
                System.out.println("|");
            }
        }
        System.out.println();
    }
}