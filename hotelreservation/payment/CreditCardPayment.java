package hotelreservation.payment;

import hotelreservation.model.Reservation;
import java.math.BigDecimal;

public class CreditCardPayment implements PaymentProcessor {

    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(Reservation reservation, BigDecimal amount) {
        System.out.println("Memproses pembayaran Credit Card (" + 
                          cardNumber.substring(cardNumber.length() - 4) + ")");
        System.out.println("Pembayaran Rp " + amount + " berhasil untuk reservasi " + 
                          reservation.getReservationId());
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "Credit Card";
    }
}