class ParkingSlot {
    private int slotNumber;
    private boolean occupied;
    private boolean isVIP;
    private boolean reserved;
    private Vehicle vehicle;

    public ParkingSlot(int slotNumber, boolean isVIP) {
        this.slotNumber = slotNumber;
        this.isVIP = isVIP;
        this.occupied = false;
        this.reserved = false;
    }

    public boolean isOccupied() { return occupied; }
    public boolean isVIP() { return isVIP; }
    public boolean isReserved() { return reserved; }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.occupied = true;
        this.reserved = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.occupied = false;
    }

    public void reserveSlot() { this.reserved = true; }
    public Vehicle getVehicle() { return vehicle; }
    public int getSlotNumber() { return slotNumber; }
}
