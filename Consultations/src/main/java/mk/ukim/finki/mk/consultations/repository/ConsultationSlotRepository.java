package mk.ukim.finki.mk.consultations.repository;

import mk.ukim.finki.mk.consultations.model.ConsultationSlot;
import mk.ukim.finki.mk.consultations.model.vm.Page;
import mk.ukim.finki.mk.consultations.service.ConsultationSlotService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ConsultationSlotRepository {
    ConsultationSlot save(ConsultationSlot slot);

    Page<ConsultationSlot> getAllConsultationSlots(int page, int size);

    List<ConsultationSlot> searchConsultationSlots(String term);

    Optional<ConsultationSlot> findById(int slotId);

    void deleteById(int slotId);
}
