// Vehicle.java
import java.time.LocalDateTime;

class Vehicle {
    private String licensePlate;
    private LocalDateTime entryTime;
    private boolean isVIP;
    private String type;             // car, motorcycle, or truck
    private int extendedHours;       // Additional hours purchased

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
        this.extendedHours = 0;
    }

    // Getter methods
    public String getLicensePlate() { return licensePlate; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public boolean isVIP() { return isVIP; }
    public String getType() { return type; }

    /**
     * Extends the parking time for this vehicle
     * @param hours Additional hours to extend
     */
    public void extendParking(int hours) {
        this.extendedHours += hours;
    }

    /**
     * Calculates the estimated exit time based on extended hours
     * @return Estimated exit time
     */
    public LocalDateTime getEstimatedExitTime() {
        // Modified: 1 hour = 10 seconds
        return entryTime.plusSeconds(extendedHours * 10);
    }
}