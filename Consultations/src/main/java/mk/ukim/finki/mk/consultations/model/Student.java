package mk.ukim.finki.mk.consultations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    @Column(name = "student_index")
    private String index;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @ManyToMany(mappedBy = "followers")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Professor> following;

    public void follow(Professor professor) {
        this.following.add(professor);
        professor.getFollowers().add(this);
    }

    public void unFollow(Professor professor) {
        this.following.remove(professor);
        professor.getFollowers().remove(this);
    }
}
