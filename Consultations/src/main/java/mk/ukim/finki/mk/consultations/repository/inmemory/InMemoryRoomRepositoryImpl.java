package mk.ukim.finki.mk.consultations.repository.inmemory;

import mk.ukim.finki.mk.consultations.model.Room;
import mk.ukim.finki.mk.consultations.model.bootstrap.DataHolder;
import mk.ukim.finki.mk.consultations.repository.RoomRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryRoomRepositoryImpl implements RoomRepository {
    @Override
    public Room save(Room room) {
        this.findById(room.getName()).ifPresent(DataHolder.rooms::remove);
        DataHolder.rooms.add(room);
        return room;
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(DataHolder.rooms);
    }

    @Override
    public List<Room> searchRooms(String term) {
        return DataHolder.rooms.stream()
                .filter(r -> r.matches(term))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Room> findById(String name) {
        return DataHolder.rooms.stream()
                .filter(r -> r.getName().equals(name))
                .findFirst();
    }

    @Override
    public void deleteById(String name) {
        this.findById(name).ifPresent(DataHolder.rooms::remove);
    }
}
