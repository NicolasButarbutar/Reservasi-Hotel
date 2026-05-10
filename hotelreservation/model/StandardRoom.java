package hotelreservation.model;

import java.math.BigDecimal;

public class StandardRoom extends Room {

    public StandardRoom(String roomNumber,
                        BigDecimal basePricePerNight) {

        super(roomNumber, "Standard", basePricePerNight, 2);
    }

    @Override
    protected void printAdditionalDetails() {
        System.out.println(
            "Fasilitas   : WiFi, TV, AC, Kamar Mandi"
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