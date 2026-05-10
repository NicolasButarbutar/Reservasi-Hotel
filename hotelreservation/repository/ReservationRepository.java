package hotelreservation.repository;

import hotelreservation.model.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    
    void save(Reservation reservation);
    Optional<Reservation> findById(String reservationId);
    List<Reservation> findByGuestId(String guestId);
    List<Reservation> findAllActive();
    void cancel(String reservationId);
}