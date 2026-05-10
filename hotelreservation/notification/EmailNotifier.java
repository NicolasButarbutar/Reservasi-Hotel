package hotelreservation.notification;

import hotelreservation.model.Guest;

public class EmailNotifier implements Notifier {

    @Override
    public void sendConfirmation(Guest guest, String message) {
        System.out.println("[EMAIL] Dikirim ke " + guest.getEmail());
        System.out.println("Subject: Reservasi Berhasil");
        System.out.println("Pesan: " + message);
        System.out.println("----------------------------------------");
    }

    @Override
    public void sendCancellation(Guest guest, String message) {
        System.out.println("[EMAIL] Dikirim ke " + guest.getEmail());
        System.out.println("Subject: Reservasi Dibatalkan");
        System.out.println("Pesan: " + message);
        System.out.println("----------------------------------------");
    }
}