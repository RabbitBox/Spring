package mk.ukim.finki.mk.consultations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Professor {

    @Id
    private String id;

    private String title;

    private String firstName;

    private String lastName;

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> followers;

    public boolean matches(String term) {
        return this.firstName.matches(term) ||
                this.lastName.matches(term) ||
                this.title.matches(term);
    }
}
