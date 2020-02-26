package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.vm.Page;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/ingredients")
public class IngredientApi {

    private final IngredientService ingredientService;

    public IngredientApi(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient addIngredient(@RequestParam("name") String name,
                                    @RequestParam("spicy") boolean spicy,
                                    @RequestParam("amount") float amount,
                                    @RequestParam("veggie") boolean veggie,
                                    @RequestParam("pizzaNames") String ... pizzaNames){

        return ingredientService.save(name, spicy, amount, veggie, pizzaNames);
    }

    @PatchMapping("/{ingredientId}")
    public Ingredient updateIngredient(@PathVariable String ingredientId,
                                       @RequestParam boolean spicy,
                                       @RequestParam float amount,
                                       @RequestParam boolean veggie,
                                       @RequestParam String ... pizzaNames){
        return ingredientService.editIngredient(ingredientId, spicy, amount, veggie, pizzaNames);
    }

    @DeleteMapping("/{ingredientId}")
    public void deleteIngredient(@PathVariable String ingredientId){
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  " + ingredientId);
         ingredientService.deleteIngredientById(ingredientId);
    }

    @GetMapping("/page")
    public Page<Ingredient> listIngredients(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                      @RequestHeader(name = "page-size", defaultValue = "10", required = false) int size){

        return ingredientService.listIngredients(page, size);
    }

    @GetMapping("/all")
    public List<Ingredient> listAllIngredients(){
        return ingredientService.listAllIngredients();
    }

    @GetMapping("/{ingredientId}")
    public Ingredient getIngredient(@PathVariable String ingredientId){
        return ingredientService.getById(ingredientId);
    }

    @GetMapping(params = "term")
    public List<Ingredient> getAllIngredientsBySpicy(@RequestParam boolean term){
        System.out.println(term);
        return ingredientService.getAllIngredientsBySpicy(term);
    }

    @GetMapping("/{id}/pizzas")
    public List<Pizza> getAllPizzasWithIngredient(@PathVariable String id) {
        System.out.println("assssssssssssssssssssssssssssssssssssssssssssssssssss");
        return ingredientService.getAllPizzasWithIngredient(id);
    }




}
