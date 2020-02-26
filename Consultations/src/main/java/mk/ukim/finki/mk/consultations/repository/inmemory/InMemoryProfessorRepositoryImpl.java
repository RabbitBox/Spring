package mk.ukim.finki.mk.consultations.repository.inmemory;

import mk.ukim.finki.mk.consultations.model.Professor;
import mk.ukim.finki.mk.consultations.model.Student;
import mk.ukim.finki.mk.consultations.model.bootstrap.DataHolder;
import mk.ukim.finki.mk.consultations.model.vm.Page;
import mk.ukim.finki.mk.consultations.repository.ProfessorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProfessorRepositoryImpl implements ProfessorRepository {
    @Override
    public Optional<Professor> findById(String professorId) {
        return DataHolder.professors.stream()
                .filter(p -> p.getId().equals(professorId))
                .findFirst();
    }

    @Override
    public Professor save(Professor professor) {
        this.findById(professor.getId()).ifPresent(DataHolder.professors::remove);
        DataHolder.professors.add(professor);
        return professor;
    }

    @Override
    public Page<Student> getFollowers(String professorId, int page, int pageSize) {
        List<Student> content = this.findById(professorId).orElse(new Professor()).getFollowers();
        return Page.slice(content, page, pageSize);
    }
}
