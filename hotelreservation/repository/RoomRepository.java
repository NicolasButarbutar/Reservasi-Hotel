package hotelreservation.repository;

import hotelreservation.model.Room;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    
    void addRoom(Room room);
    Optional<Room> findByRoomNumber(String roomNumber);
    List<Room> findAllAvailable();
    List<Room> findAll();
    List<Room> searchRooms(String keyword);
    void updateRoomAvailability(String roomNumber, boolean available);
}