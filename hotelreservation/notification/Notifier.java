package hotelreservation.notification;

import hotelreservation.model.Guest;

public interface Notifier {
    void sendConfirmation(Guest guest, String message);
    void sendCancellation(Guest guest, String message);
}