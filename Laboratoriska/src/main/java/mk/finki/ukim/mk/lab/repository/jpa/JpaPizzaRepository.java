package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JpaPizzaRepository extends JpaRepository<Pizza, String> {

  // KAKO DA NAPRAVAM KVERI KOE STO KE ZIMA PODATOCI OD MEGUTABELATA ????????????

    @Query("select pizza from Pizza pizza join pizza.pizzaIngredient ings where size(ings) > :total ") // ovie dve se napisano od demonstratoro , kaza deka prave join na pizaa i na megutabelata i so size gi broe, ''''''' AKO PRISTAPAM BILO KOE SVOJSTVO NA INGREDIENT primer ings.nesto avtomatski ke me spoe i so ingredient tabelata
    List<Pizza> findAllByPizzaIngredientsIsGreaterThan(@Param("total") Long total); // pizzaIngredient e propertito
}
