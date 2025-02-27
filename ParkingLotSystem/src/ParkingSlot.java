class ParkingSlot {
    private int slotNumber;
    private boolean occupied;
    private boolean isVIP;
    private Vehicle vehicle;

    public ParkingSlot(int slotNumber, boolean isVIP) {
        this.slotNumber = slotNumber;
        this.isVIP = isVIP;
        this.occupied = false;
    }

    public boolean isOccupied() { return occupied; }
    public boolean isVIP() { return isVIP; }
    public int getSlotNumber() { return slotNumber; }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.occupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.occupied = false;
    }

    public Vehicle getVehicle() { return vehicle; }
}