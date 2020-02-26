package mk.ukim.finki.mk.consultations.service;

import mk.ukim.finki.mk.consultations.model.Student;
import mk.ukim.finki.mk.consultations.model.vm.Page;

public interface FollowService {

    void follow(String studentIndex, String professorId);

    void unFollow(String studentIndex, String professorId);

    Page<Student> getFollowers(String professorId, int page, int pageSize);
}