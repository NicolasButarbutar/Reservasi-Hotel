package hotelreservation.repository;

import hotelreservation.model.Room;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryRoomRepository implements RoomRepository {

    private final Map<String, Room> rooms = new HashMap<>();

    @Override
    public void addRoom(Room room) {
        rooms.put(room.getRoomNumber(), room);
    }

    @Override
    public Optional<Room> findByRoomNumber(String roomNumber) {
        return Optional.ofNullable(rooms.get(roomNumber));
    }

    @Override
    public List<Room> findAllAvailable() {
        return rooms.values().stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(rooms.values());
    }

    @Override
    public List<Room> searchRooms(String keyword) {
        return rooms.values().stream()
                .filter(room -> room.matches(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public void updateRoomAvailability(String roomNumber, boolean available) {
        findByRoomNumber(roomNumber).ifPresent(room -> room.setAvailable(available));
    }
}