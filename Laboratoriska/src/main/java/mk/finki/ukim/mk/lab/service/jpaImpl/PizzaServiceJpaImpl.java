package mk.finki.ukim.mk.lab.service.jpaImpl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.excaptions.InvalidIngredientId;
import mk.finki.ukim.mk.lab.repository.IngredientRepo;
import mk.finki.ukim.mk.lab.repository.jpa.JpaPizzaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PizzaServiceJpaImpl implements PizzaService {

    private final JpaPizzaRepository jpaPizzaRepository;
    private final IngredientRepo ingredientsRepository;

    public PizzaServiceJpaImpl(JpaPizzaRepository jpaPizzaRepository, IngredientRepo ingredientsRepository) {
        this.jpaPizzaRepository = jpaPizzaRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public List<Pizza> listPizzas() {
        return jpaPizzaRepository.findAll();
    }

    @Override
    public Pizza addPizza(String name, String description, boolean veggie, String[] ingredientsNames) {
        List<Ingredient> ingredients = ingredientsRepository.findAllById(Arrays.asList(ingredientsNames));
        Pizza pizza = new Pizza(name, description, ingredients, veggie);
        return jpaPizzaRepository.save(pizza);
    }

    @Override
    public Pizza editIngredient(String name, String description, boolean veggie, String[] ingredientsNames) {
        jpaPizzaRepository.deleteById(name);
        List<Ingredient> ingredients = ingredientsRepository.findAllById(Arrays.asList(ingredientsNames));
        return jpaPizzaRepository.save(new Pizza(name, description, ingredients , veggie));
    }

    @Override
    public void deletePizza(String pizzaId) {
        jpaPizzaRepository.deleteById(pizzaId);
    }

    @Override
    public Pizza getPizza(String id) {
        return jpaPizzaRepository.findById(id).orElseThrow(InvalidIngredientId::new);
    }

    @Override
    public List<Pizza> getAllPizzasWithLessIngredients(int totalIngredients) {
        return null;
    }
}
