package mk.ukim.finki.mk.consultations.repository.jpa;


import mk.ukim.finki.mk.consultations.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStudentRepository extends JpaRepository<Student, String> {
}
