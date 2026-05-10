package hotelreservation.model;

import java.time.LocalDate;
import java.math.BigDecimal;

public interface Bookable {
    boolean book(Guest guest, LocalDate checkIn, LocalDate checkOut);
    boolean cancelBooking(String reservationId);
    BigDecimal calculateTotalPrice(int nights);
}