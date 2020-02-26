package mk.finki.ukim.mk.lab.model.staticData;

import lombok.Getter;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.jpa.JpaIngredientsRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaOrderRepository;
import mk.finki.ukim.mk.lab.repository.jpa.JpaPizzaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Pizza> pizzas = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<Ingredient> ingredients = new ArrayList<>();

    public final JpaIngredientsRepository jpaIngredientsRepository;
    public final JpaPizzaRepository jpaPizzaRepository;
    public final JpaOrderRepository jpaOrderRepository;

    public DataHolder(JpaIngredientsRepository jpaIngredientsRepository, JpaPizzaRepository jpaPizzaRepository, JpaOrderRepository jpaOrderRepository) {
        this.jpaPizzaRepository = jpaPizzaRepository;
        this.jpaIngredientsRepository = jpaIngredientsRepository;
        this.jpaOrderRepository = jpaOrderRepository;
    }

    public static Long counter;

    @PostConstruct
    public void init(){
        counter = 1L;

        orders.add(new Order(getNextId(), "Maritaras", "Big", "ace", "karpois"));
        orders.add(new Order(getNextId(), "Maritaras", "Big", "ace", "karpois"));
        orders.add(new Order(getNextId(), "Maritaras", "Big", "ace", "karpois"));
        orders.add(new Order(getNextId(), "Maritaras", "Big", "ace", "karpois"));


        pizzas.add(new Pizza("Margherita", "(tomato sauce, mozzarella)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Carbonara", "(fresh cream, mozzarella, bacon)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Vegetariana", "(tomato sauce, mushrooms)", new ArrayList<>(), true));
        pizzas.add(new Pizza("Calzone", "(Pizza dough, ricotta, pepperoni, pizza sauce, olive oil)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Cheddar", "(cheddar, tomato sauce)", new ArrayList<>(), true));
        pizzas.add(new Pizza("Capricciosa", "(tomato sauce, cheese, ham)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Burger Classic", "(barbecue sauce, beef, mozzarella, onions)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Burger Barbecue", "(ham, chicken meat, onions)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Pepperoni", "(tomato sauce, mozzarella, sausage)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Quattro Formaggi", "(Taleggio, Mascarpone, Gorgonzola, Parmesan)", new ArrayList<>(), false));

        Ingredient in1 = new Ingredient("mushroom", false, 30, true, new ArrayList<>());
        Ingredient in2 = new Ingredient("ham", false, 50, false, new ArrayList<>());
        Ingredient in3 = new Ingredient("peperoni", true, 50, false, new ArrayList<>());
        Ingredient in4 = new Ingredient("cheder", false, 30, true, new ArrayList<>());

        ingredients.add(in1);
        ingredients.add(in2);
        ingredients.add(in3);
        ingredients.add(in4);

        pizzas.get(0).addIngredient(in1);
        pizzas.get(1).addIngredient(in1);
        pizzas.get(2).addIngredient(in1);
        pizzas.get(3).addIngredient(in1);
        pizzas.get(4).addIngredient(in1);
        pizzas.get(5).addIngredient(in1);
        pizzas.get(6).addIngredient(in1);
        pizzas.get(7).addIngredient(in1);
        pizzas.get(8).addIngredient(in1);
        pizzas.get(9).addIngredient(in1);
        pizzas.get(0).addIngredient(in2);
        pizzas.get(1).addIngredient(in2);
        pizzas.get(2).addIngredient(in3);
        pizzas.get(3).addIngredient(in3);

        if(jpaPizzaRepository.count() == 0){
            jpaOrderRepository.saveAll(orders);
            jpaIngredientsRepository.saveAll(ingredients);
            jpaPizzaRepository.saveAll(pizzas);
        }



    }

    public static Long getNextId(){
        return counter++;
    }
}
