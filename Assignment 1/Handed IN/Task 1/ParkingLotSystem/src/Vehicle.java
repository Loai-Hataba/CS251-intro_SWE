// Vehicle.java
import java.time.LocalDateTime;

class Vehicle {
    private String licensePlate;
    private LocalDateTime entryTime;
    private boolean isVIP;
    private String type;             //could be a car motorcycle or truck

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