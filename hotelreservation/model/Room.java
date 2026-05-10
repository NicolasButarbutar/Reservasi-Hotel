package hotelreservation.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Room implements Bookable, Searchable {

    protected String roomNumber;
    protected String type;
    protected BigDecimal basePricePerNight;
    protected int capacity;
    protected boolean available;

    public Room(String roomNumber,
                String type,
                BigDecimal basePricePerNight,
                int capacity) {

        this.roomNumber = roomNumber;
        this.type = type;
        this.basePricePerNight = basePricePerNight;
        this.capacity = capacity;
        this.available = true;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getBasePricePerNight() {
        return basePricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public BigDecimal calculateTotalPrice(int nights) {
        return basePricePerNight.multiply(BigDecimal.valueOf(nights));
    }

    @Override
    public boolean book(Guest guest,
                        LocalDate checkIn,
                        LocalDate checkOut) {

        if (!available) {
            return false;
        }

        available = false;
        return true;
    }

    @Override
    public boolean cancelBooking(String reservationId) {
        available = true;
        return true;
    }

    public void displayInfo() {

        System.out.println("Nomor Kamar : " + roomNumber);
        System.out.println("Tipe        : " + type);
        System.out.println("Harga/Malam : " + basePricePerNight);
        System.out.println("Kapasitas   : " + capacity);
        System.out.println("Tersedia    : " + available);

        printAdditionalDetails();

        System.out.println("--------------------------------");
    }

    protected abstract void printAdditionalDetails();
}