package mk.ukim.finki.mk.consultations.repository.inmemory;

import mk.ukim.finki.mk.consultations.model.Student;
import mk.ukim.finki.mk.consultations.model.bootstrap.DataHolder;
import mk.ukim.finki.mk.consultations.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryStudentRepositoryImpl implements StudentRepository {
    @Override
    public Optional<Student> findById(String studentIndex) {
        return DataHolder.students.stream()
                .filter(s -> s.getIndex().equals(studentIndex))
                .findFirst();
    }

    @Override
    public void save(Student student) {
        this.findById(student.getIndex()).ifPresent(DataHolder.students::remove);
        DataHolder.students.add(student);
    }
}
