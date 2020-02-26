package mk.ukim.finki.mk.consultations.service;

import mk.ukim.finki.mk.consultations.model.Building;
import mk.ukim.finki.mk.consultations.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room createRoom(String name, Building building, String description);

    List<Room> getAllRooms();

    List<Room> searchRooms(String term);

    Room updateRoom(String oldName, String name, Building building, String description);

    void deleteRoom(String name);

    Optional<Room> findByName(String name);
}
