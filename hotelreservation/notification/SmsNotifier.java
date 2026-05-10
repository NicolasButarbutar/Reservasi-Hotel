package hotelreservation.notification;

import hotelreservation.model.Guest;

public class SmsNotifier implements Notifier {

    @Override
    public void sendConfirmation(Guest guest, String message) {
        System.out.println("[SMS] Dikirim ke " + guest.getPhoneNumber());
        System.out.println("Pesan: " + message);
        System.out.println("----------------------------------------");
    }

    @Override
    public void sendCancellation(Guest guest, String message) {
        System.out.println("[SMS] Dikirim ke " + guest.getPhoneNumber());
        System.out.println("Pesan: " + message);
        System.out.println("----------------------------------------");
    }
}