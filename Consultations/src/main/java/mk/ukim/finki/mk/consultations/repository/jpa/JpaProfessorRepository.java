package mk.ukim.finki.mk.consultations.repository.jpa;


import mk.ukim.finki.mk.consultations.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProfessorRepository  extends JpaRepository<Professor, String> {
}
