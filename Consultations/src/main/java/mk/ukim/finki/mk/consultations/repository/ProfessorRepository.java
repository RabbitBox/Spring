package mk.ukim.finki.mk.consultations.repository;

import mk.ukim.finki.mk.consultations.model.Professor;
import mk.ukim.finki.mk.consultations.model.Student;
import mk.ukim.finki.mk.consultations.model.vm.Page;

import java.util.Optional;

public interface ProfessorRepository {
    Optional<Professor> findById(String professorId);

    Professor save(Professor professor);

    Page<Student> getFollowers(String professorId, int page, int pageSize);
}
