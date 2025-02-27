import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class ParkingLot {
    private List<ParkingSlot> slots;                // List of all parking slots
    private List<String> parkingHistory;            // History of parking transactions
    private Map<String, Integer> vehicleTypeCount;  // Count of each vehicle type
    private int totalVehiclesParked;                // Total vehicles that have parked
    private double totalRevenue;                    // Total revenue collected

    /**
     * Creates a new parking lot with the specified capacity
     * @param capacity Number of parking slots
     */
    public ParkingLot(int capacity) {
        slots = new ArrayList<>();
        parkingHistory = new ArrayList<>();
        vehicleTypeCount = new HashMap<>();
        vehicleTypeCount.put("car", 0);
        vehicleTypeCount.put("motorcycle", 0);
        vehicleTypeCount.put("truck", 0);
        totalVehiclesParked = 0;
        totalRevenue = 0;

        // Initialize parking slots (first 2 are VIP)
        for (int i = 1; i <= capacity; i++) {
            slots.add(new ParkingSlot(i, i <= 2));
        }
    }

    /**
     * Parks a vehicle in an available slot
     * @param licensePlate Vehicle license plate
     * @param isVIP Whether to use a VIP slot
     * @param vehicleType Type of vehicle (car, motorcycle, truck)
     * @return true if parking successful, false otherwise
     */
    public boolean parkVehicle(String licensePlate, boolean isVIP, String vehicleType) {
        // Validate vehicle type
        if (!vehicleType.equals("car") && !vehicleType.equals("motorcycle") && !vehicleType.equals("truck")) {
            System.out.println("Invalid vehicle type! Using default: car");
            vehicleType = "car";
        }

        // Check if vehicle already exists in the lot
        Optional<ParkingSlot> existingVehicle = slots.stream()
                .filter(slot -> slot.isOccupied() && slot.getVehicle().getLicensePlate().equals(licensePlate))
                .findFirst();

        if (existingVehicle.isPresent()) {
            System.out.println("Vehicle with this license plate already parked in slot " +
                    existingVehicle.get().getSlotNumber());
            return false;
        }

        // Find available slot of the requested type (VIP or regular)
        Optional<ParkingSlot> availableSlot = slots.stream()
                .filter(slot -> !slot.isOccupied() && !slot.isReserved() && slot.isVIP() == isVIP)
                .findFirst();

        if (availableSlot.isPresent()) {
            availableSlot.get().parkVehicle(new Vehicle(licensePlate, isVIP, vehicleType));
            System.out.println("Vehicle parked in slot " + availableSlot.get().getSlotNumber());

            // Update statistics
            totalVehiclesParked++;
            vehicleTypeCount.put(vehicleType, vehicleTypeCount.get(vehicleType) + 1);

            return true;
        } else {
            System.out.println("No available " + (isVIP ? "VIP " : "") + "slots!");

            // Suggest other slot types if available
            if (isVIP) {
                // Check if regular slots are available as an alternative
                long regularSlots = slots.stream()
                        .filter(slot -> !slot.isOccupied() && !slot.isReserved() && !slot.isVIP())
                        .count();

                if (regularSlots > 0) {
                    System.out.println("There are " + regularSlots + " regular slots available.");
                    System.out.println("Would you like to use a regular slot instead? (yes/no)");
                    Scanner scanner = new Scanner(System.in);
                    String choice = scanner.nextLine();

                    if (choice.equalsIgnoreCase("yes")) {
                        return parkVehicle(licensePlate, false, vehicleType);
                    }
                }
            }

            return false;
        }
    }

    /**
     * Removes a vehicle from the parking lot
     * @param licensePlate License plate of the vehicle to remove
     * @return true if removal successful, false if vehicle not found
     */
    public boolean removeVehicle(String licensePlate) {
        // Find the slot containing the vehicle with the given license plate
        Optional<ParkingSlot> occupiedSlot = slots.stream()
                .filter(slot -> slot.isOccupied() && slot.getVehicle().getLicensePlate().equals(licensePlate))
                .findFirst();

        if (occupiedSlot.isPresent()) {
            ParkingSlot slot = occupiedSlot.get();
            Vehicle vehicle = slot.getVehicle();

            // Calculate parking duration and fee
            long secsParked = Duration.between(vehicle.getEntryTime(), LocalDateTime.now()).toSeconds();
            double fee = ParkingFeeCalc.calculateFee(vehicle.getEntryTime(), vehicle.isVIP(), vehicle.getType());

            // Update total revenue
            totalRevenue += fee;

            // Remove vehicle from slot
            slot.removeVehicle();

            // Create and store record in parking history
            String record = "Vehicle " + licensePlate + " (" + vehicle.getType() + ") removed. " +
                    "Parked for: " + formatDuration(secsParked) + ". Fee: $" + String.format("%.2f", fee);

            parkingHistory.add(record);
            System.out.println(record);
            displayGrid();
            return true;
        } else {
            System.out.println("Vehicle not found!");
            return false;
        }
    }

    /**
     * Displays a visual representation of the parking lot
     */
    public void displayGrid() {
        // Count available slots of each type
        long availableSlots = slots.stream().filter(slot -> !slot.isOccupied() && !slot.isReserved()).count();
        long vipAvailable = slots.stream().filter(slot -> !slot.isOccupied() && !slot.isReserved() && slot.isVIP()).count();
        long regularAvailable = slots.stream().filter(slot -> !slot.isOccupied() && !slot.isReserved() && !slot.isVIP()).count();

        System.out.println("\nParking Lot Status:");
        for (int i = 0; i < slots.size(); i++) {
            ParkingSlot slot = slots.get(i);
            System.out.print("[");

            // Display different characters based on slot state and vehicle type
            if (slot.isOccupied()) {
                Vehicle v = slot.getVehicle();
                if (v.getType().equals("car")) {
                    System.out.print("C");
                } else if (v.getType().equals("motorcycle")) {
                    System.out.print("M");
                } else if (v.getType().equals("truck")) {
                    System.out.print("T");
                }
            } else if (slot.isVIP()) {
                System.out.print("V");
            } else if (slot.isReserved()) {
                System.out.print("R");
            } else {
                System.out.print(" ");
            }

            System.out.print("] ");

            // Create new line after every 5 slots
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }

        // Display availability summary
        System.out.println("\nAvailable slots: " + availableSlots +
                " (VIP: " + vipAvailable + ", Regular: " + regularAvailable + ")");
        System.out.println("Legend: [C]=Car [M]=Motorcycle [T]=Truck [V]=VIP Available [R]=Reserved [ ]=Regular Available");
    }

    /**
     * Reserves a parking slot for a specific duration
     * @param slotNumber The slot number to reserve
     * @param hours Duration of reservation in hours
     */
    public void reserveSlot(int slotNumber, int hours) {
        if (slotNumber > 0 && slotNumber <= slots.size()) {
            ParkingSlot slot = slots.get(slotNumber - 1);
            if (slot.isOccupied()) {
                System.out.println("Cannot reserve an occupied slot!");
            } else {
                // Set reservation with expiry time (1 hour = 10 seconds)
                slot.reserveSlot(hours);
                System.out.println("Slot " + slotNumber + " reserved for " + hours + " hours.");
                System.out.println("Reservation will expire at: " +
                        slot.getReservationExpiry().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                System.out.println("NOTE: For testing purposes, 1 hour = 10 seconds.");
            }
        } else {
            System.out.println("Invalid slot number!");
        }
    }

    /**
     * Displays the history of all parking transactions
     */
    public void displayParkingHistory() {
        if (parkingHistory.isEmpty()) {
            System.out.println("\nNo parking history available.");
            return;
        }

        System.out.println("\nParking History:");
        for (int i = 0; i < parkingHistory.size(); i++) {
            System.out.println((i + 1) + ". " + parkingHistory.get(i));
        }
    }

    /**
     * Admin mode showing detailed information about all slots
     */
    public void adminMode() {
        System.out.println("\nAdmin mode activated: Displaying all slots");
        for (ParkingSlot slot : slots) {
            System.out.print("Slot " + slot.getSlotNumber() +
                    " | VIP: " + slot.isVIP() +
                    " | Occupied: " + slot.isOccupied() +
                    " | Reserved: " + slot.isReserved());

            // Show additional info if slot is occupied
            if (slot.isOccupied()) {
                Vehicle v = slot.getVehicle();
                System.out.print(" | License: " + v.getLicensePlate() +
                        " | Type: " + v.getType() +
                        " | Entry: " + v.getEntryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            } else if (slot.isReserved()) {
                System.out.print(" | Expires: " + slot.getReservationExpiry().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }

            System.out.println();
        }

        // Display current rate information
        System.out.println("\nCurrent rates:");
        System.out.println("Regular rate: $" + ParkingFeeCalc.getRegularRate() + " per hour");
        System.out.println("VIP rate: $" + ParkingFeeCalc.getVIPRate() + " per hour");
        System.out.println("Vehicle modifiers: Motorcycle (-30%), Truck (+50%)");
        System.out.println("NOTE: For testing purposes, 1 hour = 10 seconds.");
    }

    /**
     * Searches for a vehicle by license plate and displays information
     * @param licensePlate License plate to search for
     */
    public void searchVehicle(String licensePlate) {
        // Find the slot containing the vehicle with the given license plate
        Optional<ParkingSlot> occupiedSlot = slots.stream()
                .filter(slot -> slot.isOccupied() && slot.getVehicle().getLicensePlate().equals(licensePlate))
                .findFirst();

        if (occupiedSlot.isPresent()) {
            ParkingSlot slot = occupiedSlot.get();
            Vehicle vehicle = slot.getVehicle();

            // Calculate current duration and fee
            long secsParked = Duration.between(vehicle.getEntryTime(), LocalDateTime.now()).toSeconds();
            double currentFee = ParkingFeeCalc.calculateFee(vehicle.getEntryTime(), vehicle.isVIP(), vehicle.getType());

            // Display vehicle information
            System.out.println("\nVehicle found:");
            System.out.println("License plate: " + vehicle.getLicensePlate());
            System.out.println("Vehicle type: " + vehicle.getType());
            System.out.println("Parked in slot: " + slot.getSlotNumber() + (slot.isVIP() ? " (VIP)" : ""));
            System.out.println("Entry time: " + vehicle.getEntryTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println("Parked for: " + formatDuration(secsParked));
            System.out.println("Current fee: $" + String.format("%.2f", currentFee));
        } else {
            System.out.println("Vehicle not found in the parking lot.");
        }
    }

    /**
     * Extends parking time for a vehicle
     * @param licensePlate License plate of the vehicle
     * @param hours Additional hours to extend
     */
    public void extendParking(String licensePlate, int hours) {
        if (hours <= 0) {
            System.out.println("Hours must be greater than 0!");
            return;
        }

        // Find the slot containing the vehicle with the given license plate
        Optional<ParkingSlot> occupiedSlot = slots.stream()
                .filter(slot -> slot.isOccupied() && slot.getVehicle().getLicensePlate().equals(licensePlate))
                .findFirst();

        if (occupiedSlot.isPresent()) {
            Vehicle vehicle = occupiedSlot.get().getVehicle();

            // Update the vehicle's extended hours and calculate fee
            vehicle.extendParking(hours);
            double additionalFee = ParkingFeeCalc.calculateExtendedFee(hours, vehicle.isVIP(), vehicle.getType());

            System.out.println("Parking extended by " + hours + " hours.");
            System.out.println("Additional fee: $" + String.format("%.2f", additionalFee));
            System.out.println("New exit time estimate: " +
                    vehicle.getEstimatedExitTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println("NOTE: For testing purposes, 1 hour = 10 seconds.");
        } else {
            System.out.println("Vehicle not found!");
        }
    }

    /**
     * Displays all available parking slots
     */
    public void findEmptySlot() {
        System.out.println("\nFinding available parking slots...");

        // Display available VIP slots
        boolean found = false;
        System.out.println("VIP slots:");
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied() && !slot.isReserved() && slot.isVIP()) {
                System.out.println("Slot " + slot.getSlotNumber() + " is available");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No VIP slots available");
        }

        // Display available Regular slots
        found = false;
        System.out.println("\nRegular slots:");
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied() && !slot.isReserved() && !slot.isVIP()) {
                System.out.println("Slot " + slot.getSlotNumber() + " is available");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No regular slots available");
        }
    }

    /**
     * Displays statistics about the parking lot
     */
    public void displayStatistics() {
        System.out.println("\nParking Lot Statistics:");
        System.out.println("Total vehicles parked: " + totalVehiclesParked);
        System.out.println("Total revenue: $" + String.format("%.2f", totalRevenue));

        // Display vehicle type statistics
        System.out.println("\nVehicles by type:");
        System.out.println("Cars: " + vehicleTypeCount.get("car"));
        System.out.println("Motorcycles: " + vehicleTypeCount.get("motorcycle"));
        System.out.println("Trucks: " + vehicleTypeCount.get("truck"));

        // Calculate and display current occupancy
        long occupiedCount = slots.stream().filter(slot -> slot.isOccupied()).count();
        long reservedCount = slots.stream().filter(slot -> slot.isReserved()).count();

        System.out.println("\nCurrent status:");
        System.out.println("Occupied slots: " + occupiedCount + " (" +
                String.format("%.1f", (double)occupiedCount / slots.size() * 100) + "%)");
        System.out.println("Reserved slots: " + reservedCount);
        System.out.println("Available slots: " + (slots.size() - occupiedCount - reservedCount));
    }

    /**
     * Helper method to format seconds into HH:MM:SS format
     * For testing mode, we show the actual seconds
     * @param seconds Duration in seconds
     * @return Formatted string in HH:MM:SS format with note about accelerated time
     */
    private String formatDuration(long seconds) {
        // Show both the accelerated time and the actual seconds
        long normalHours = seconds / 3600;
        long normalMinutes = (seconds % 3600) / 60;
        long normalSecs = seconds % 60;

        // Calculate hours in accelerated time (1 hour = 10 seconds)
        double acceleratedHours = seconds / 10.0;

        return String.format("%02d:%02d:%02d (%.1f accelerated hours, 1 hour = 10 seconds)",
                normalHours, normalMinutes, normalSecs, acceleratedHours);
    }

    /**
     * Checks and updates expired reservations
     */
    public void checkReservations() {
        LocalDateTime now = LocalDateTime.now();
        for (ParkingSlot slot : slots) {
            if (slot.isReserved() && now.isAfter(slot.getReservationExpiry())) {
                System.out.println("Reservation for slot " + slot.getSlotNumber() + " has expired.");
                slot.clearReservation();
            }
        }
    }
}