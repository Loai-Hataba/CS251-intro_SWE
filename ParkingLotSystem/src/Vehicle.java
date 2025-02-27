import java.time.LocalDateTime;

class Vehicle {
    private String licensePlate;
    private LocalDateTime entryTime;
    private boolean isVIP;

    public Vehicle(String licensePlate, boolean isVIP) {
        this.licensePlate = licensePlate;
        this.entryTime = LocalDateTime.now();
        this.isVIP = isVIP;
    }

    public String getLicensePlate() { return licensePlate; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public boolean isVIP() { return isVIP; }
}
