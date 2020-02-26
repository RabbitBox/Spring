package mk.finki.ukim.mk.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient {

    @Id
    private String name;

    private boolean spicy;

    private float amount;

    private boolean veggie;

    @JsonIgnore
    @ManyToMany(mappedBy = "pizzaIngredients")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Pizza> pizzas;
}
