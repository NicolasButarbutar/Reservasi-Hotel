package hotelreservation.model;

import java.math.BigDecimal;

public class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber,
                      BigDecimal basePricePerNight) {

        super(roomNumber, "Deluxe", basePricePerNight, 3);
    }

    @Override
    protected void printAdditionalDetails() {
        System.out.println(
            "Fasilitas   : WiFi, TV, AC, Mini Bar, Bathtub"
        );
    }

    @Override
    public boolean matches(String keyword) {

        return getRoomNumber().toLowerCase()
                .contains(keyword.toLowerCase())

            ||

               getType().toLowerCase()
                .contains(keyword.toLowerCase());
    }
}