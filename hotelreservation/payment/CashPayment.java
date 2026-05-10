package hotelreservation.payment;

import hotelreservation.model.Reservation;
import java.math.BigDecimal;

public class CashPayment implements PaymentProcessor {

    @Override
    public boolean processPayment(Reservation reservation, BigDecimal amount) {
        System.out.println("Pembayaran Cash diterima sebesar Rp " + amount);
        System.out.println("Reservasi " + reservation.getReservationId() + " berhasil dibayar tunai.");
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "Cash";
    }
}