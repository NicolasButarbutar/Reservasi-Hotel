package hotelreservation.model;

import java.math.BigDecimal;

public class SuiteRoom extends Room {

    public SuiteRoom(String roomNumber,
                     BigDecimal basePricePerNight) {

        super(roomNumber, "Suite", basePricePerNight, 5);
    }

    @Override
    protected void printAdditionalDetails() {
        System.out.println(
            "Fasilitas   : WiFi, TV, Jacuzzi, Ruang Tamu"
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