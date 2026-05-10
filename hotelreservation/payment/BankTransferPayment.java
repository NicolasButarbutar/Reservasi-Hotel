package hotelreservation.payment;

import hotelreservation.model.Reservation;
import java.math.BigDecimal;

public class BankTransferPayment implements PaymentProcessor {

    @Override
    public boolean processPayment(Reservation reservation, BigDecimal amount) {
        System.out.println("Pembayaran via Bank Transfer sebesar Rp " + amount);
        System.out.println("Silakan transfer ke rekening: 1234567890 a.n. Hotel Reservation");
        System.out.println("Reservasi " + reservation.getReservationId() + " akan dikonfirmasi setelah pembayaran.");
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "Bank Transfer";
    }
}