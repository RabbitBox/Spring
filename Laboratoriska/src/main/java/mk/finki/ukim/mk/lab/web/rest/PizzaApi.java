package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pizzas")
public class PizzaApi {
    private final PizzaService pizzaService;

    public PizzaApi(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    public Pizza addPizza(@RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam(value = "veggie", defaultValue = "false") boolean veggie,
                               @RequestParam("ingredientsNames") String ... ingredientsNames){

        return pizzaService.addPizza(name, description, veggie, ingredientsNames);
    }

    @PutMapping("/{pizzaId}")
    public Pizza updatePizza(@PathVariable("pizzaId") String name,
                                       @RequestParam("description") String description,
                                       @RequestParam(value = "veggie") boolean veggie,
                                       @RequestParam("ingredientsNames") String ... ingredientsNames){

        return pizzaService.editIngredient(name, description, veggie, ingredientsNames);
    }

    @DeleteMapping("/{pizzaId}")
    public void deletePizza(@PathVariable String pizzaId){
        pizzaService.deletePizza(pizzaId);
    }

    @GetMapping
    public List<Pizza> listPizzas(){
        return pizzaService.listPizzas();
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable String id){
        return pizzaService.getPizza(id);
    }

    @GetMapping(params = "totalIngredients")
    public List<Pizza> getAllPizzasWithLessIngredients(@RequestParam int totalIngredients){
        return pizzaService.getAllPizzasWithLessIngredients(totalIngredients);
    }
}
