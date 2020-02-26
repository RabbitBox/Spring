package mk.finki.ukim.mk.lab.service.jpaImpl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.excaptions.InvalidIngredientId;
import mk.finki.ukim.mk.lab.model.vm.Page;
import mk.finki.ukim.mk.lab.repository.IngredientRepo;
import mk.finki.ukim.mk.lab.repository.jpa.JpaIngredientsRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaPizzaRepository;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class IngredientServiceJpaImpl implements IngredientService {

    private final IngredientRepo ingredientsRepository;
    private final JpaPizzaRepository jpaPizzaRepository;

    public IngredientServiceJpaImpl(IngredientRepo ingredientsRepository, JpaPizzaRepository jpaPizzaRepository) {
        this.ingredientsRepository = ingredientsRepository;
        this.jpaPizzaRepository = jpaPizzaRepository;
    }

    @Override
    public Page<Ingredient> listIngredients(int page, int size) {
        return ingredientsRepository.findAll(page, size);
    }

    @Override
    public Ingredient save(String name, boolean spicy, float amount, boolean veggie, String [] pizzaNames) {

        if(ingredientsRepository.getNumberOfSpicyIngredients() < 3 || !spicy) {
            List<Pizza> pizzas = jpaPizzaRepository.findAllById(Arrays.asList(pizzaNames));
            Ingredient ingredient = new Ingredient(name, spicy, amount, veggie, new ArrayList<>());

            for (Pizza pizza : pizzas) {
                pizza.addIngredient(ingredient);
            }
            return ingredientsRepository.save(ingredient);
        }
        else throw new InvalidIngredientId();
    }

    @Override
    public Ingredient editIngredient(String ingredientId, boolean spicy, float amount, boolean veggie, String[] pizzaNames) {
        Ingredient ingredient = ingredientsRepository.findById(ingredientId);
        List<Pizza> pizzas = jpaPizzaRepository.findAllById(Arrays.asList(pizzaNames));
        ingredient.setAmount(amount);
        ingredient.setSpicy(spicy);
        ingredient.setVeggie(veggie);

        List<Pizza> oldPizzas = ingredient.getPizzas();
        for(Pizza p : oldPizzas){
            p.getPizzaIngredients().remove(ingredient);
        }


        ingredient.setPizzas(new ArrayList<>());
        for(Pizza p : pizzas){
            p.addIngredient(ingredient);
        }
        return ingredientsRepository.save(ingredient);
    }

    @Override
    public void deleteIngredientById(String ingredientId) {
        Ingredient ingredient = ingredientsRepository.findById(ingredientId);
        List<Pizza> oldPizzas = ingredient.getPizzas();
        for(Pizza p : oldPizzas){
            p.getPizzaIngredients().remove(ingredient);
        }
        ingredient.setPizzas(new ArrayList<>());
        ingredientsRepository.save(ingredient);
        ingredientsRepository.delete(ingredient);
    }

    @Override
    public Ingredient getById(String ingredientId) {
        return ingredientsRepository.getById(ingredientId);
    }

    @Override
    public List<Ingredient> getAllIngredientsBySpicy(boolean term) {
        return ingredientsRepository.getAllIngredientsBySpicy(term);
    }

    @Override
    public List<Pizza> getAllPizzasWithIngredient(String id) {
        Ingredient ingredient = ingredientsRepository.findById(id);
        return ingredient.getPizzas();
    }

    @Override
    public List<Ingredient> listAllIngredients() {
        return ingredientsRepository.listAllIngredients();
    }
}
