import java.time.LocalDateTime;

class ParkingSlot {
    private int slotNumber;
    private boolean occupied;
    private boolean isVIP;
    private boolean reserved;
    private Vehicle vehicle;
    private LocalDateTime reservationExpiry;  // When the reservation expires

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

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.occupied = true;
        this.reserved = false;  //Clear any reservation when a vehicle parks
    }

    //Removes a vehicle form this slot
    public void removeVehicle() {
        this.vehicle = null;
        this.occupied = false;
    }
    //Reserves a slot for a specified duration
    public void reserveSlot(int hours) {
        this.reserved = true;
        //1 hour = 10 seconds
        this.reservationExpiry = LocalDateTime.now().plusSeconds(hours * 10);
    }
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