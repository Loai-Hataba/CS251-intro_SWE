import java.time.Duration;
import java.time.LocalDateTime;

class ParkingFeeCalc {
    // Base rates for parking fees
    private static double REGULAR_RATE = 2.0;
    private static double VIP_RATE = 4.0;

    // Modifiers for different vehicle types
    private static final double MOTORCYCLE_DISCOUNT = 0.7;  // 30% discount for motorcycles
    private static final double TRUCK_SURCHARGE = 1.5;      // 50% surcharge for trucks

    /**
     * Calculates parking fee based on time parked and vehicle information
     * @param entryTime When the vehicle entered the parking lot
     * @param isVIP Whether the vehicle is in a VIP slot
     * @param vehicleType Type of vehicle (car, motorcycle, truck)
     * @return Calculated fee
     */
    public static double calculateFee(LocalDateTime entryTime, boolean isVIP, String vehicleType) {
        // Calculate duration in seconds
        long secs = Duration.between(entryTime, LocalDateTime.now()).toSeconds();
        double baseRate = isVIP ? VIP_RATE : REGULAR_RATE;

        // Apply vehicle type modifiers
        if (vehicleType.equals("motorcycle")) {
            baseRate *= MOTORCYCLE_DISCOUNT;
        } else if (vehicleType.equals("truck")) {
            baseRate *= TRUCK_SURCHARGE;
        }

        // Calculate fee based on seconds (1 hour = 10 seconds)
        // We charge per second instead of per 10-second interval for accurate testing
        return (secs / 10.0) * baseRate;
    }

    /**
     * Updates the parking rates
     * @param regularRate New rate for regular parking
     * @param vipRate New rate for VIP parking
     */
    public static void updateRates(double regularRate, double vipRate) {
        REGULAR_RATE = regularRate;
        VIP_RATE = vipRate;
    }

    public static double getRegularRate() {
        return REGULAR_RATE;
    }

    public static double getVIPRate() {
        return VIP_RATE;
    }
}