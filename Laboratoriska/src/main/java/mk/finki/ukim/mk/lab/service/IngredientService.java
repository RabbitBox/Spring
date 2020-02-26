package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.vm.Page;

import java.util.List;

public interface IngredientService {
    Page<Ingredient> listIngredients(int page, int size);

    Ingredient save(String name, boolean spicy, float amount, boolean veggie, String [] pizzaNames);

    Ingredient editIngredient(String ingredientId, boolean spicy, float amount, boolean veggie, String[] pizzaNames);

    void deleteIngredientById(String ingredientId);

    Ingredient getById(String ingredientId);

    List<Ingredient> getAllIngredientsBySpicy(boolean term);

    List<Pizza> getAllPizzasWithIngredient(String id);

    List<Ingredient> listAllIngredients();
}
