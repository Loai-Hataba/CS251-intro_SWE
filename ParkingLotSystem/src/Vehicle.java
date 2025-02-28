// Vehicle.java
import java.time.LocalDateTime;

class Vehicle {
    private String licensePlate;
    private LocalDateTime entryTime;
    private boolean isVIP;
    private String type;             // car, motorcycle, or truck

    /**
     * Creates a new vehicle
     * @param licensePlate The vehicle's license plate
     * @param isVIP Whether this vehicle uses a VIP slot
     * @param type Type of vehicle (car, motorcycle, truck)
     */
    public Vehicle(String licensePlate, boolean isVIP, String type) {
        this.licensePlate = licensePlate;
        this.entryTime = LocalDateTime.now();
        this.isVIP = isVIP;
        this.type = type;
    }

    // Getter methods
    public String getLicensePlate() { return licensePlate; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public boolean isVIP() { return isVIP; }
    public String getType() { return type; }
}