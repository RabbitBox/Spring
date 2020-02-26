package mk.ukim.finki.mk.consultations.repository.jpa;


import mk.ukim.finki.mk.consultations.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoomRepository extends JpaRepository<Room, String> {
}
