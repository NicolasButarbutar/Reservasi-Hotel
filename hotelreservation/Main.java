package hotelreservation;

import hotelreservation.model.*;
import hotelreservation.repository.*;
import hotelreservation.service.*;
import hotelreservation.payment.*;
import hotelreservation.notification.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("=========================================");
        System.out.println("   SISTEM RESERVASI HOTEL - SOLID Demo");
        System.out.println("=========================================\n");

        // Inisialisasi Repository & Service
        RoomRepository roomRepo = new InMemoryRoomRepository();
        ReservationRepository reservationRepo = new InMemoryReservationRepository();

        PaymentProcessor paymentProcessor = new CreditCardPayment("4111-XXXX-XXXX-1111");
        Notifier notifier = new EmailNotifier();

        ReservationService reservationService = new ReservationService(
                roomRepo, reservationRepo, paymentProcessor, notifier);
        
        ReportService reportService = new ReportService(reservationRepo);

        // Tambahkan beberapa kamar awal
        initializeRooms(roomRepo);

        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Tambah Data Tamu & Buat Reservasi");
            System.out.println("2. Lihat Semua Kamar");
            System.out.println("3. Lihat Reservasi Aktif");
            System.out.println("4. Batalkan Reservasi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    buatReservasiBaru(scanner, reservationService, formatter);
                    break;
                case 2:
                    System.out.println("\nDaftar Semua Kamar:");
                    reservationService.displayAllRooms();
                    break;
                case 3:
                    reportService.printActiveReservations();
                    break;
                case 4:
                    batalkanReservasi(scanner, reservationService);
                    break;
                case 0:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan sistem reservasi hotel.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }

        scanner.close();
    }

    private static void initializeRooms(RoomRepository roomRepo) {
        roomRepo.addRoom(new StandardRoom("101", new BigDecimal("450000")));
        roomRepo.addRoom(new DeluxeRoom("201", new BigDecimal("750000")));
        roomRepo.addRoom(new SuiteRoom("301", new BigDecimal("1200000")));
        roomRepo.addRoom(new StandardRoom("102", new BigDecimal("450000")));
        roomRepo.addRoom(new DeluxeRoom("202", new BigDecimal("750000")));
    }

    private static void buatReservasiBaru(Scanner scanner, ReservationService service, DateTimeFormatter formatter) {
        System.out.println("\n=== BUAT RESERVASI BARU ===");

        // Input Data Tamu
        System.out.print("Masukkan ID Tamu (contoh: G001): ");
        String guestId = scanner.nextLine();

        System.out.print("Masukkan Nama Tamu: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Email: ");
        String email = scanner.nextLine();

        System.out.print("Masukkan Nomor Telepon: ");
        String telepon = scanner.nextLine();

        Guest guest = new Guest(guestId, nama, email, telepon);

        // Tampilkan kamar yang tersedia
        System.out.println("\nKamar yang tersedia saat ini:");
        service.getAvailableRooms().forEach(Room::displayInfo);

        System.out.print("\nMasukkan Nomor Kamar yang diinginkan: ");
        String roomNumber = scanner.nextLine();

        System.out.print("Masukkan Tanggal Check-in (yyyy-MM-dd): ");
        LocalDate checkIn = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Masukkan Tanggal Check-out (yyyy-MM-dd): ");
        LocalDate checkOut = LocalDate.parse(scanner.nextLine(), formatter);

        try {
            Reservation reservation = service.makeReservation(guest, roomNumber, checkIn, checkOut);
            System.out.println("\n✅ Reservasi berhasil dibuat!");
            System.out.println("ID Reservasi: " + reservation.getReservationId());
        } catch (Exception e) {
            System.out.println("❌ Gagal membuat reservasi: " + e.getMessage());
        }
    }

    private static void batalkanReservasi(Scanner scanner, ReservationService service) {
        System.out.print("\nMasukkan ID Reservasi yang ingin dibatalkan: ");
        String reservationId = scanner.nextLine();
        service.cancelReservation(reservationId);
    }
}