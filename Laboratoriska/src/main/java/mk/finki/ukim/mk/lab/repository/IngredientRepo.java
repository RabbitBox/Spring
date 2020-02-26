package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.vm.Page;

import java.util.List;
import java.util.Optional;

public interface IngredientRepo {
    Page<Ingredient> findAll(int page, int size);

    Ingredient save(Ingredient ingredient);

    Ingredient findById(String ingredientId);

    void delete(Ingredient ingredient);

    Ingredient getById(String ingredientId);

    List<Ingredient> getAllIngredientsBySpicy(boolean term);

    Integer getNumberOfSpicyIngredients();

    List<Ingredient> findAllById(List<String> asList);

    List<Ingredient> listAllIngredients();
}
