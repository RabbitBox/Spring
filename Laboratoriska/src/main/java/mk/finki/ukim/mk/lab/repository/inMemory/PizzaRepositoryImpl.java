package mk.finki.ukim.mk.lab.repository.inMemory;

import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.staticData.DataHolder;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PizzaRepositoryImpl implements PizzaRepository {
    @Override
    public List<Pizza> getAllPizzas() {
        return new ArrayList<>(DataHolder.pizzas);
    }


    @Override
    public Pizza save(Pizza pizza) {
        this.findByName(pizza.getName()).ifPresent(DataHolder.pizzas::remove);
        DataHolder.pizzas.add(pizza);
        return pizza;
    }

    @Override
    public Optional<Pizza> findByName(String name) {
        return DataHolder.pizzas.stream()
                .filter(r -> r.getName().equals(name))
                .findFirst();
    }
}
