package hotelreservation.payment;

import hotelreservation.model.Reservation;
import java.math.BigDecimal;

public interface PaymentProcessor {
    boolean processPayment(Reservation reservation, BigDecimal amount);
    String getPaymentMethod();
}