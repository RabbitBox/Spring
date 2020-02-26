package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("in-memory")
@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }


    @Override
    public List<Pizza> listPizzas() {
        return this.pizzaRepository.getAllPizzas();
    }


    @Override
    public Pizza addPizza(String name, String description, boolean veggie, String[] ingredientsNames) {
        return null;
    }

    @Override
    public Pizza editIngredient(String name, String description, boolean veggie, String[] ingredientsNames) {
        return null;
    }

    @Override
    public void deletePizza(String pizzaId) {

    }

    @Override
    public Pizza getPizza(String id) {
        return null;
    }

    @Override
    public List<Pizza> getAllPizzasWithLessIngredients(int totalIngredients) {
        return null;
    }
}
