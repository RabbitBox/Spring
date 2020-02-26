package mk.ukim.finki.mk.consultations.model.bootstrap;

import lombok.Getter;
import mk.ukim.finki.mk.consultations.model.*;
import mk.ukim.finki.mk.consultations.repository.jpa.JpaConsultationSlotRepository;
import mk.ukim.finki.mk.consultations.repository.jpa.JpaProfessorRepository;
import mk.ukim.finki.mk.consultations.repository.jpa.JpaRoomRepository;
import mk.ukim.finki.mk.consultations.repository.jpa.JpaStudentRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {


    public static final List<Room> rooms = new ArrayList<>();

    public static final List<Student> students = new ArrayList<>();

    public static final List<Professor> professors = new ArrayList<>();

    public static final List<ConsultationSlot> slots = new ArrayList<>();

    public final JpaRoomRepository roomRepository;

    public final JpaStudentRepository studentRepository;

    public final JpaProfessorRepository professorRepository;

    public final JpaConsultationSlotRepository consultationSlotRepository;

    public DataHolder(JpaRoomRepository roomRepository, JpaStudentRepository studentRepository, JpaProfessorRepository professorRepository, JpaConsultationSlotRepository consultationSlotRepository) {
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.consultationSlotRepository = consultationSlotRepository;
    }

    @PostConstruct
    public void init() {

        rooms.add(new Room("117", Building.TMF, "на приземје, ходник десно од главниот влез, последна врата од лево (просторијата каде се вршеше запишувањето на ФИНКИ)"));
        rooms.add(new Room("114", Building.TMF, "на приземје, ходник десно од главниот влез, простории од десна страна"));
        rooms.add(new Room("115", Building.TMF, "на приземје, ходник десно од главниот влез, простории од десна страна"));
        rooms.add(new Room("116", Building.TMF, "на приземје, ходник десно од главниот влез, простории од десна страна"));
        rooms.add(new Room("201", Building.TMF, "први спрат, десно од скалите, па повторно десно"));
        rooms.add(new Room("203", Building.TMF, "први спрат, десно од скалите, па повторно десно"));
        rooms.add(new Room("2xx", Building.TMF, "први спрат, лево од скалите, па повторно лево"));
        rooms.add(new Room("315", Building.TMF, "втори спрат, лево од скалите, па повторно лево"));
        rooms.add(new Room("301", Building.TMF, "втори кат, десно од скалите, па повторно десно"));
        rooms.add(new Room("302", Building.TMF, "втори кат, десно од скалите, па повторно десно"));
        rooms.add(new Room("123(Ф)", Building.MF, "прв спрат, десно од скалите"));
        rooms.add(new Room("112(Ф)", Building.MF, "прв спрат, десно од скалите"));
        rooms.add(new Room("223(М)", Building.MF, "втор спрат, лево од скалите"));
        rooms.add(new Room("225(М)", Building.MF, "втор спрат, десно од скалите"));
        rooms.add(new Room("B1", Building.B, ""));
        rooms.add(new Room("B2.1", Building.B, ""));
        rooms.add(new Room("B2.2", Building.B, ""));
        rooms.add(new Room("B3.1", Building.B, ""));
        rooms.add(new Room("B3.2", Building.B, ""));
        rooms.add(new Room("B3.2", Building.B, ""));
        rooms.add(new Room("B3.4", Building.B, ""));

        students.add(new Student("170001", "Petko", "Petkovski", new ArrayList<>()));
        students.add(new Student("170002", "Petko", "Petkovski", new ArrayList<>()));
        students.add(new Student("170003", "Petko", "Petkovski", new ArrayList<>()));
        students.add(new Student("170004", "Petko", "Petkovski", new ArrayList<>()));
        students.add(new Student("170005", "Petko", "Petkovski", new ArrayList<>()));
        students.add(new Student("170006", "Petko", "Petkovski", new ArrayList<>()));

        Professor dt = new Professor("dimitar.trajanov", "проф. д-р", "Димитар", "Трајанов", new ArrayList<>());
        Professor rs = new Professor("riste.stojanov", "доц. д-р", "Ристе", "Стојанов", new ArrayList<>());
        Professor km = new Professor("kostadin.mishev", "м-р", "Костадин", "Мишев", new ArrayList<>());

        professors.add(dt);
        professors.add(rs);
        professors.add(km);

        students.get(0).follow(dt);
        students.get(1).follow(dt);
        students.get(2).follow(dt);
        students.get(3).follow(dt);
        students.get(4).follow(dt);
        students.get(5).follow(dt);
        students.get(0).follow(km);
        students.get(1).follow(km);
        students.get(2).follow(km);

        slots.add(ConsultationSlot.createRecurringSlot(dt, rooms.get(1), DayOfWeek.TUESDAY, LocalTime.parse("10:00"), LocalTime.parse("12:00")));
        slots.add(ConsultationSlot.createRecurringSlot(dt, rooms.get(1), DayOfWeek.THURSDAY, LocalTime.parse("19:00"), LocalTime.parse("21:00")));


        slots.add(ConsultationSlot.createRecurringSlot(rs, rooms.get(1), DayOfWeek.TUESDAY, LocalTime.parse("10:00"), LocalTime.parse("12:00")));
        slots.add(ConsultationSlot.createRecurringSlot(rs, rooms.get(1), DayOfWeek.THURSDAY, LocalTime.parse("19:00"), LocalTime.parse("21:00")));


        slots.add(ConsultationSlot.createOneTimeSlot(km, rooms.get(1), LocalDate.now().plusDays(7), LocalTime.parse("10:00"), LocalTime.parse("12:00")));
        slots.add(ConsultationSlot.createRecurringSlot(km, rooms.get(1), DayOfWeek.THURSDAY, LocalTime.parse("19:00"), LocalTime.parse("21:00")));


        if (this.roomRepository.count() == 0) {
            this.roomRepository.saveAll(rooms);
            this.studentRepository.saveAll(students);
            this.professorRepository.saveAll(professors);
            this.consultationSlotRepository.saveAll(slots);
        }


    }
}
