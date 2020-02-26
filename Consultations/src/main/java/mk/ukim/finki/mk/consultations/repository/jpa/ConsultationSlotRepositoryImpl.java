package mk.ukim.finki.mk.consultations.repository.jpa;


import mk.ukim.finki.mk.consultations.model.ConsultationSlot;
import mk.ukim.finki.mk.consultations.model.vm.Page;
import mk.ukim.finki.mk.consultations.repository.ConsultationSlotRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsultationSlotRepositoryImpl implements ConsultationSlotRepository {

    private final JpaConsultationSlotRepository repository;

    public ConsultationSlotRepositoryImpl(JpaConsultationSlotRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConsultationSlot save(ConsultationSlot slot) {
        return this.repository.save(slot);
    }

    @Override
    public Page<ConsultationSlot> getAllConsultationSlots(int page, int size) {

        org.springframework.data.domain.Page<ConsultationSlot> result = this.repository.findAll(PageRequest.of(page, size));
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getContent());
    }

    @Override
    public List<ConsultationSlot> searchConsultationSlots(String term) {
        return this.repository.searchConsultationSlots(term);
    }

    @Override
    public Optional<ConsultationSlot> findById(int slotId) {
        return this.repository.findById(slotId);
    }

    @Override
    public void deleteById(int slotId) {
        this.repository.deleteById(slotId);
    }
}
