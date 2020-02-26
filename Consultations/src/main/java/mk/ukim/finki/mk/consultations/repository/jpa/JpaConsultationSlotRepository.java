package mk.ukim.finki.mk.consultations.repository.jpa;


import mk.ukim.finki.mk.consultations.model.ConsultationSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaConsultationSlotRepository extends JpaRepository<ConsultationSlot, Integer> {


    @Query("select c from ConsultationSlot c " +
            "WHERE c.professor.firstName like :term or c.professor.lastName like :term")
    List<ConsultationSlot> searchConsultationSlots(@Param("term") String term);

    List<ConsultationSlot> findByProfessorIdOrRoomNameOrderByDateAsc(String professorId, String roomName);
}
