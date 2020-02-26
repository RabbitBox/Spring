package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.excaptions.InvalidIngredientId;
import mk.finki.ukim.mk.lab.model.vm.Page;
import mk.finki.ukim.mk.lab.repository.IngredientRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientRepoImplPoint implements IngredientRepo {

    private final JpaIngredientsRepository jpaIngredientsRepository;

    public IngredientRepoImplPoint(JpaIngredientsRepository jpaIngredientsRepository) {
        this.jpaIngredientsRepository = jpaIngredientsRepository;
    }

    @Override
    public Page<Ingredient> findAll(int page, int size) {

        org.springframework.data.domain.Page<Ingredient> result = this.jpaIngredientsRepository.findAll(PageRequest.of(page, size));
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getContent());
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return jpaIngredientsRepository.save(ingredient);
    }

    @Override
    public Ingredient findById(String ingredientId) {
        return jpaIngredientsRepository.findById(ingredientId).orElseThrow(InvalidIngredientId::new);
    }

    @Override
    public void delete(Ingredient ingredient) {
        jpaIngredientsRepository.delete(ingredient);
    }

    @Override
    public Ingredient getById(String ingredientId) {
        return jpaIngredientsRepository.findById(ingredientId).orElseThrow(InvalidIngredientId::new);
    }

    @Override
    public List<Ingredient> getAllIngredientsBySpicy(boolean term) {
        return jpaIngredientsRepository.findBySpicy(term);
    }

    @Override
    public Integer getNumberOfSpicyIngredients() {
        return jpaIngredientsRepository.getNumberOfSpicyIngredients();
    }

    @Override
    public List<Ingredient> findAllById(List<String> asList) {
        return jpaIngredientsRepository.findAllById(asList);
    }

    @Override
    public List<Ingredient> listAllIngredients() {
        return jpaIngredientsRepository.findAll();
    }
}
