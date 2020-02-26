package mk.ukim.finki.mk.consultations.service;

import mk.ukim.finki.mk.consultations.model.ConsultationSlot;
import mk.ukim.finki.mk.consultations.model.vm.Page;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ConsultationSlotService {

    ConsultationSlot createSlot(String professorId, String roomName, DayOfWeek dayOfWeek, LocalDate date, LocalTime from, LocalTime to);

    Page<ConsultationSlot> getAllConsultationSlots(int page, int size);

    List<ConsultationSlot> searchConsultationSlots(String term);

    ConsultationSlot updateSlot(int slotId, String professorId, String roomName, DayOfWeek dayOfWeek, LocalDate date, LocalTime from, LocalTime to);

    void deleteSlot(int slotId);
}
