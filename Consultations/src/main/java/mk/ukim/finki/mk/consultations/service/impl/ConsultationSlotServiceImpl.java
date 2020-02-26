package mk.ukim.finki.mk.consultations.service.impl;

import mk.ukim.finki.mk.consultations.model.ConsultationSlot;
import mk.ukim.finki.mk.consultations.model.Professor;
import mk.ukim.finki.mk.consultations.model.Room;
import mk.ukim.finki.mk.consultations.model.excaptions.InvalidConsultationSlotId;
import mk.ukim.finki.mk.consultations.model.excaptions.InvalidProfessorId;
import mk.ukim.finki.mk.consultations.model.excaptions.InvalidRoomName;
import mk.ukim.finki.mk.consultations.model.vm.Page;
import mk.ukim.finki.mk.consultations.repository.ConsultationSlotRepository;
import mk.ukim.finki.mk.consultations.repository.RoomRepository;
import mk.ukim.finki.mk.consultations.repository.ProfessorRepository;
import mk.ukim.finki.mk.consultations.repository.jpa.JpaProfessorRepository;
import mk.ukim.finki.mk.consultations.repository.jpa.JpaRoomRepository;
import mk.ukim.finki.mk.consultations.service.ConsultationSlotService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ConsultationSlotServiceImpl implements ConsultationSlotService {

    private final ConsultationSlotRepository consultationSlotRepository;

    private final JpaProfessorRepository professorRepository;

    private final JpaRoomRepository roomRepository;

    public ConsultationSlotServiceImpl(ConsultationSlotRepository consultationSlotRepository,       // ovaa tuka mu e nogu loso, treba da naprave klasi kako consultationSLotRepo i da gi koriste nih a ne direktno JPAREPOS
                                       JpaProfessorRepository professorRepository,
                                       JpaRoomRepository roomRepository) {
        this.consultationSlotRepository = consultationSlotRepository;
        this.professorRepository = professorRepository;
        this.roomRepository = roomRepository;
    }

//    private final ProfessorRepository professorRepository;
//
//    private final RoomRepository roomRepository;
//
//    public ConsultationSlotServiceImpl(ConsultationSlotRepository consultationSlotRepository,
//                                       ProfessorRepository professorRepository,
//                                       RoomRepository roomRepository) {
//        this.consultationSlotRepository = consultationSlotRepository;
//        this.professorRepository = professorRepository;
//        this.roomRepository = roomRepository;
//    }


    @Override
    public ConsultationSlot createSlot(String professorId, String roomName, DayOfWeek dayOfWeek, LocalDate date, LocalTime from, LocalTime to) {
        if (dayOfWeek == null && date == null) {
            throw new IllegalArgumentException();
        }
        ConsultationSlot slot;
        Professor professor = this.professorRepository.findById(professorId).orElseThrow(InvalidProfessorId::new);
        Room room = this.roomRepository.findById(roomName).orElseThrow(InvalidRoomName::new);
        if (dayOfWeek != null) {
            slot = ConsultationSlot.createRecurringSlot(professor, room, dayOfWeek, from, to);
        } else {
            slot = ConsultationSlot.createOneTimeSlot(professor, room, date, from, to);
        }
        return this.consultationSlotRepository.save(slot);
    }

    @Override
    public Page<ConsultationSlot> getAllConsultationSlots(int page, int size) {
        return this.consultationSlotRepository.getAllConsultationSlots(page, size);
    }

    @Override
    public List<ConsultationSlot> searchConsultationSlots(String term) {
        return this.consultationSlotRepository.searchConsultationSlots(term);
    }

    @Override
    public ConsultationSlot updateSlot(int slotId, String professorId, String roomName, DayOfWeek dayOfWeek, LocalDate date, LocalTime from, LocalTime to) {
        ConsultationSlot slot = this.consultationSlotRepository.findById(slotId).orElseThrow(InvalidConsultationSlotId::new);
        Professor professor = this.professorRepository.findById(professorId).orElseThrow(InvalidProfessorId::new);
        Room room = this.roomRepository.findById(roomName).orElseThrow(InvalidRoomName::new);
        slot.setProfessor(professor);
        slot.setRoom(room);
        slot.setDayOfWeek(dayOfWeek);
        slot.setDate(date);
        slot.setFrom(from);
        slot.setTo(to);
        return this.consultationSlotRepository.save(slot);
    }

    @Override
    public void deleteSlot(int slotId) {
        this.consultationSlotRepository.deleteById(slotId);
    }
}
