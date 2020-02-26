package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pizzas")
public class Pizza {

    @Id
    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> pizzaIngredients;

    private boolean veggie;

    public void addIngredient(Ingredient ingredient) {
        if (!this.getPizzaIngredients().contains(ingredient)) {
            this.pizzaIngredients.add(ingredient);
            ingredient.getPizzas().add(this);
        }
    }
}
