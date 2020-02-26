package mk.ukim.finki.mk.consultations.service.impl;

import mk.ukim.finki.mk.consultations.model.Professor;
import mk.ukim.finki.mk.consultations.model.Student;
import mk.ukim.finki.mk.consultations.model.excaptions.InvalidProfessorId;
import mk.ukim.finki.mk.consultations.model.excaptions.InvalidStudentIndex;
import mk.ukim.finki.mk.consultations.model.vm.Page;
import mk.ukim.finki.mk.consultations.repository.ProfessorRepository;
import mk.ukim.finki.mk.consultations.repository.StudentRepository;
import mk.ukim.finki.mk.consultations.service.FollowService;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    private final ProfessorRepository professorRepository;

    private final StudentRepository studentRepository;

    public FollowServiceImpl(ProfessorRepository professorRepository, StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void follow(String studentIndex, String professorId) {
        Student student = this.studentRepository.findById(studentIndex).orElseThrow(InvalidStudentIndex::new);
        Professor professor = this.professorRepository.findById(professorId).orElseThrow(InvalidProfessorId::new);
        student.follow(professor);
        this.studentRepository.save(student);
        this.professorRepository.save(professor);
    }

    @Override
    public void unFollow(String studentIndex, String professorId) {
        Student student = this.studentRepository.findById(studentIndex).orElseThrow(InvalidStudentIndex::new);
        Professor professor = this.professorRepository.findById(professorId).orElseThrow(InvalidProfessorId::new);
        student.unFollow(professor);
        this.studentRepository.save(student);
        this.professorRepository.save(professor);
    }

    @Override
    public Page<Student> getFollowers(String professorId, int page, int pageSize) {
        return this.professorRepository.getFollowers(professorId, page, pageSize);
    }
}
