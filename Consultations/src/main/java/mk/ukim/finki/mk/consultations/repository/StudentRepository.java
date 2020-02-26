package mk.ukim.finki.mk.consultations.repository;

import mk.ukim.finki.mk.consultations.model.Student;

import java.util.Optional;

public interface StudentRepository {
    Optional<Student> findById(String studentIndex);

    void save(Student student);
}
