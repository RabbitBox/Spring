package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JpaIngredientsRepository extends JpaRepository<Ingredient, String> {

//    @Query("select i from Ingredient i " +                    // rabote i ovaa
//            "WHERE i.spicy = :term")
//    List<Ingredient> findBySpicy(@Param("term") boolean term);

    List<Ingredient> findBySpicy(boolean term);

    @Query("select count(c.spicy) from Ingredient c " +
            "WHERE c.spicy = true")
    Integer getNumberOfSpicyIngredients();



}
