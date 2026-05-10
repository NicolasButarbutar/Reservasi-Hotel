package hotelreservation.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.math.BigDecimal;

public class Reservation {
    private String reservationId;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean isActive = true;

    public Reservation(String reservationId, Guest guest, Room room, 
                      LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getNumberOfNights() {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public BigDecimal getTotalPrice() {
        return room.getBasePricePerNight().multiply(BigDecimal.valueOf(getNumberOfNights()));
    }

    // Getter & Setter
    public String getReservationId() { return reservationId; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public boolean isActive() { return isActive; }

    public void cancel() {
        this.isActive = false;
        this.room.setAvailable(true);
    }
}