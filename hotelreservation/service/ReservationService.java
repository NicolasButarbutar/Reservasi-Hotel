package hotelreservation.service;

import hotelreservation.model.*;
import hotelreservation.payment.PaymentProcessor;
import hotelreservation.notification.Notifier;
import hotelreservation.repository.RoomRepository;
import hotelreservation.repository.ReservationRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ReservationService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentProcessor paymentProcessor;
    private final Notifier notifier;

    // Constructor Injection (DIP)
    public ReservationService(RoomRepository roomRepository,
                              ReservationRepository reservationRepository,
                              PaymentProcessor paymentProcessor,
                              Notifier notifier) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.paymentProcessor = paymentProcessor;
        this.notifier = notifier;
    }

    public Reservation makeReservation(Guest guest, String roomNumber, 
                                     LocalDate checkIn, LocalDate checkOut) {
        
        // Validasi tanggal
        if (checkIn.isAfter(checkOut) || checkIn.isEqual(checkOut)) {
            throw new IllegalArgumentException("Tanggal check-out harus setelah check-in");
        }

        Room room = roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new IllegalArgumentException("Kamar tidak ditemukan: " + roomNumber));

        if (!room.isAvailable()) {
            throw new IllegalArgumentException("Kamar " + roomNumber + " tidak tersedia");
        }

        // Buat reservasi
        String reservationId = "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Reservation reservation = new Reservation(reservationId, guest, room, checkIn, checkOut);

        // Hitung total harga
        BigDecimal totalPrice = reservation.getTotalPrice();

        // Proses pembayaran
        boolean paymentSuccess = paymentProcessor.processPayment(reservation, totalPrice);
        
        if (!paymentSuccess) {
            throw new RuntimeException("Pembayaran gagal");
        }

        // Simpan reservasi
        room.setAvailable(false);
        reservationRepository.save(reservation);

        // Kirim notifikasi
        String message = String.format("Reservasi berhasil! Kamar %s (%s) dari %s sampai %s. Total: Rp %s",
                room.getRoomNumber(), room.getType(), checkIn, checkOut, totalPrice);
        
        notifier.sendConfirmation(guest, message);

        System.out.println("✅ Reservasi berhasil dibuat dengan ID: " + reservationId);
        return reservation;
    }

    public void cancelReservation(String reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservasi tidak ditemukan"));

        if (!reservation.isActive()) {
            System.out.println("Reservasi sudah dibatalkan sebelumnya.");
            return;
        }

        reservation.cancel();
        reservationRepository.cancel(reservationId);

        notifier.sendCancellation(reservation.getGuest(), 
            "Reservasi " + reservationId + " telah dibatalkan.");

        System.out.println("✅ Reservasi " + reservationId + " berhasil dibatalkan.");
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findAllAvailable();
    }

    public List<Reservation> getAllActiveReservations() {
        return reservationRepository.findAllActive();
    }

    public void displayAllRooms() {
        roomRepository.findAll().forEach(Room::displayInfo);
    }
}