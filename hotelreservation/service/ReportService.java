package hotelreservation.service;

import hotelreservation.model.Reservation;
import hotelreservation.repository.ReservationRepository;
import java.util.List;

public class ReportService {

    private final ReservationRepository reservationRepository;

    public ReportService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void printActiveReservations() {
        List<Reservation> reservations = reservationRepository.findAllActive();
        
        System.out.println("\n=== LAPORAN RESERVASI AKTIF ===");
        System.out.println("Total Reservasi Aktif: " + reservations.size());
        
        for (Reservation r : reservations) {
            System.out.println("ID: " + r.getReservationId() + 
                             " | Tamu: " + r.getGuest().getName() +
                             " | Kamar: " + r.getRoom().getRoomNumber() +
                             " | Check-in: " + r.getCheckInDate());
        }
    }
}