import java.time.Duration;
import java.time.LocalDateTime;

class ParkingFeeCalc {
    private static final double REGULAR_RATE = 2.0;
    private static final double VIP_RATE = 4.0;

    public static double calculateFee(LocalDateTime entryTime, boolean isVIP) {
        long secs = java.time.Duration.between(entryTime, LocalDateTime.now()).toSeconds();
        return Math.ceil(secs / 10) * (isVIP ? VIP_RATE : REGULAR_RATE);
    }
}
