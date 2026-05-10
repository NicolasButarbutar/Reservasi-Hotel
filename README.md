# 🏨 Mini Project: HotelReservation System

## 📖 Definisi Proyek

**HotelReservation System** adalah aplikasi berbasis console untuk mengelola reservasi kamar hotel secara interaktif. Sistem ini memungkinkan pengguna memasukkan data tamu secara manual, memilih kamar yang tersedia, melakukan reservasi, memproses pembayaran, serta membatalkan reservasi. 

Aplikasi ini dirancang dengan menerapkan **kelima prinsip SOLID** dalam Object-Oriented Programming agar kode menjadi modular, extensible, maintainable, dan mudah diuji.

## 🛠️ Implementasi Materi Object-Oriented Programming

Proyek ini dibangun berdasarkan materi **SOLID Principles** dari perkuliahan Object-Oriented Programming:

### 1. Single Responsibility Principle (SRP)
- Setiap class memiliki satu tanggung jawab saja.
- Contoh: `ReportService` hanya menangani laporan, `Notifier` hanya menangani pengiriman notifikasi.

### 2. Open-Closed Principle (OCP)
- Kelas terbuka untuk ekstensi, tertutup untuk modifikasi.
- Contoh: Mudah menambahkan tipe kamar baru (`PenthouseRoom`) atau metode pembayaran baru tanpa mengubah class yang sudah ada.

### 3. Liskov Substitution Principle (LSP)
- Subclass dapat menggantikan superclass tanpa merusak fungsionalitas.
- Contoh: `StandardRoom`, `DeluxeRoom`, dan `SuiteRoom` dapat digunakan secara bergantian di mana `Room` diharapkan.

### 4. Interface Segregation Principle (ISP)
- Menggunakan banyak interface kecil dan spesifik daripada satu interface besar.
- Contoh: `Bookable`, `Searchable`, `PaymentProcessor`, dan `Notifier`.

### 5. Dependency Inversion Principle (DIP)
- High-level module bergantung pada abstraction, bukan pada implementasi konkret.
- Contoh: `ReservationService` bergantung pada interface `RoomRepository`, `PaymentProcessor`, dan `Notifier` melalui Constructor Injection.

## 📂 Struktur Proyek

```text
com.hotelreservation/
├── model/
│   ├── Room.java (abstract)
│   ├── StandardRoom.java
│   ├── DeluxeRoom.java
│   ├── SuiteRoom.java
│   ├── Guest.java
│   ├── Reservation.java
│   ├── Bookable.java
│   └── Searchable.java
├── repository/
│   ├── RoomRepository.java
│   ├── ReservationRepository.java
│   ├── InMemoryRoomRepository.java
│   └── InMemoryReservationRepository.java
├── payment/
│   ├── PaymentProcessor.java
│   ├── CashPayment.java
│   ├── CreditCardPayment.java
│   └── BankTransferPayment.java
├── notification/
│   ├── Notifier.java
│   ├── EmailNotifier.java
│   └── SmsNotifier.java
├── service/
│   ├── ReservationService.java
│   └── ReportService.java
├── exception/
│   └── HotelException.java
└── Main.java

## 💻 Simulasi Input & Output
``` text
Skenario: Pembuatan Reservasi Baru
=========================================
   SISTEM RESERVASI HOTEL - SOLID Demo
=========================================

=== MENU UTAMA ===
1. Tambah Data Tamu & Buat Reservasi
2. Lihat Semua Kamar
3. Lihat Reservasi Aktif
4. Batalkan Reservasi
0. Keluar
Pilih menu: 1

=== BUAT RESERVASI BARU ===
Masukkan ID Tamu (contoh: G001): G007
Masukkan Nama Tamu: Andi Saputra
Masukkan Email: andi@email.com
Masukkan Nomor Telepon: 081234567890

Kamar yang tersedia saat ini:
=== Deluxe Room ===
Nomor Kamar : 201
...

Masukkan Nomor Kamar yang diinginkan: 201
Masukkan Tanggal Check-in (yyyy-MM-dd): 2026-05-15
Masukkan Tanggal Check-out (yyyy-MM-dd): 2026-05-18

✅ Reservasi berhasil dibuat!
ID Reservasi: RES-9A3F2K1L

## ✨ Fitur Utama

Input data tamu secara manual (tidak hardcoded)
- Manajemen kamar dengan tipe berbeda
- Reservasi dan pembatalan reservasi
- Simulasi pembayaran multi-metode
- Notifikasi konfirmasi & pembatalan
- Laporan reservasi aktif
- Menu interaktif berbasis console

📝 Referensi Materi

- Simaremare, Mario. 14-01-SOLID Principles: An Introduction. Program Studi Sarjana Sistem Informasi, Institut Teknologi Del.
- Simaremare, Mario. 14-02a-Single Responsibility Principle. Program Studi Sarjana Sistem Informasi, Institut Teknologi Del.
- Simaremare, Mario. 14-02b-Open-Closed Principle. Program Studi Sarjana Sistem Informasi, Institut Teknologi Del.
- Simaremare, Mario. 14-02c-Liskov Substitution Principle. Program Studi Sarjana Sistem Informasi, Institut Teknologi Del.
- Simaremare, Mario. 14-02d-Dependency Inversion Principle. Program Studi Sarjana Sistem Informasi, Institut Teknologi Del.
- Simaremare, Mario. 14-02e-Interface Segregation Principle. Program Studi Sarjana Sistem Informasi, Institut Teknologi Del.