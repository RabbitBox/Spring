package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;

import java.util.List;

public interface PizzaService {
    List<Pizza> listPizzas();

    Pizza addPizza(String name, String description, boolean veggie, String[] ingredientsNames);

    Pizza editIngredient(String name, String description, boolean veggie, String[] ingredientsNames);

    void deletePizza(String pizzaId);

    Pizza getPizza(String id);

    List<Pizza> getAllPizzasWithLessIngredients(int totalIngredients);
}
