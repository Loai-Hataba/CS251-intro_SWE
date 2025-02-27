import java.time.LocalDateTime;

class ParkingSlot {
    private int slotNumber;
    private boolean occupied;
    private boolean isVIP;
    private boolean reserved;
    private Vehicle vehicle;
    private LocalDateTime reservationExpiry;  // When the reservation expires

    /**
     * Creates a new parking slot
     * @param slotNumber The slot number
     * @param isVIP Whether this is a VIP slot
     */
    public ParkingSlot(int slotNumber, boolean isVIP) {
        this.slotNumber = slotNumber;
        this.isVIP = isVIP;
        this.occupied = false;
        this.reserved = false;
    }

    // Getter methods
    public boolean isOccupied() { return occupied; }
    public boolean isVIP() { return isVIP; }
    public boolean isReserved() { return reserved; }

    /**
     * Parks a vehicle in this slot
     * @param vehicle The vehicle to park
     */
    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.occupied = true;
        this.reserved = false;  // Clear any reservation when a vehicle parks
    }

    /**
     * Removes a vehicle from this slot
     */
    public void removeVehicle() {
        this.vehicle = null;
        this.occupied = false;
    }

    /**
     * Reserves this slot for a specified duration
     * @param hours Duration of reservation in hours
     */
    public void reserveSlot(int hours) {
        this.reserved = true;
        // Modified: 1 hour = 10 seconds (use seconds instead of hours)
        this.reservationExpiry = LocalDateTime.now().plusSeconds(hours * 10);
    }  // Fixed: Added missing closing brace here

    /**
     * Clears the reservation on this slot
     */
    public void clearReservation() {
        this.reserved = false;
        this.reservationExpiry = null;
    }

    public LocalDateTime getReservationExpiry() {
        return reservationExpiry;
    }

    public Vehicle getVehicle() { return vehicle; }
    public int getSlotNumber() { return slotNumber; }
}