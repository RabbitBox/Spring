package mk.ukim.finki.mk.consultations.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data                   // getter and setter
public class Room {

        @Id
        private String name;

        @Enumerated(EnumType.STRING)
        private Building building;

        private String description;

        public boolean matches(String term) {
                return this.name.contains(term) ||
                        this.description.contains(term) ||
                        this.building.getDescription().contains(term);
        }

}
