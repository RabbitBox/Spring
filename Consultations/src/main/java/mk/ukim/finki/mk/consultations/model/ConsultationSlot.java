package mk.ukim.finki.mk.consultations.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class ConsultationSlot {

    private ConsultationSlot() {

    }

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static int slotsCounter = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slotId;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Room room;

    private LocalDate date;

    private DayOfWeek dayOfWeek;

    @Column(name = "from_time")
    private LocalTime from;

    @Column(name = "to_time")
    private LocalTime to;


    public static synchronized ConsultationSlot createRecurringSlot(Professor professor, Room room, DayOfWeek dayOfWeek, LocalTime from, LocalTime to) {
        ConsultationSlot slot = new ConsultationSlot();
        slot.slotId = slotsCounter;
        slotsCounter++;
        slot.professor = professor;
        slot.room = room;
        slot.dayOfWeek = dayOfWeek;
        slot.from = from;
        slot.to = to;
        return slot;
    }

    public static synchronized ConsultationSlot createOneTimeSlot(Professor professor, Room room, LocalDate date, LocalTime from, LocalTime to) {
        ConsultationSlot slot = new ConsultationSlot();
        slot.slotId = slotsCounter;
        slotsCounter++;
        slot.professor = professor;
        slot.room = room;
        slot.date = date;
        slot.from = from;
        slot.to = to;
        return slot;
    }

}