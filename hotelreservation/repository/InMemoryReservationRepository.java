package hotelreservation.repository;

import hotelreservation.model.Reservation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryReservationRepository implements ReservationRepository {

    private final Map<String, Reservation> reservations = new HashMap<>();

    @Override
    public void save(Reservation reservation) {
        reservations.put(reservation.getReservationId(), reservation);
    }

    @Override
    public Optional<Reservation> findById(String reservationId) {
        return Optional.ofNullable(reservations.get(reservationId));
    }

    @Override
    public List<Reservation> findByGuestId(String guestId) {
        return reservations.values().stream()
                .filter(r -> r.getGuest().getGuestId().equals(guestId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findAllActive() {
        return reservations.values().stream()
                .filter(Reservation::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public void cancel(String reservationId) {
        reservations.get(reservationId).cancel();
    }
}