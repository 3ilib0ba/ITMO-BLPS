package evgesha.blps.lab1.service;


import evgesha.blps.lab1.entity.Ingredient;
import evgesha.blps.lab1.exception.IngredientNotFoundException;
import evgesha.blps.lab1.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    private final MeasureService measureService;

    public IngredientService(
            IngredientRepository ingredientRepository,
            MeasureService measureService
    ) {
        this.ingredientRepository = ingredientRepository;
        this.measureService = measureService;
    }

    public Ingredient checkIngredientForMeasureAndSave(Ingredient ingredient) {
        ingredient.setMeasure(
                measureService.checkMeasure(ingredient.getMeasure())
        );
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> deleteIngredients(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(this::deleteIngredient)
                .collect(Collectors.toList());
    }

    public Ingredient deleteIngredient(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
        return ingredient;
    }

    public Ingredient deleteIngredientById(Long ingredientId) {
        Ingredient ingredient = getIngredientById(ingredientId);
        ingredientRepository.deleteById(ingredientId);
        return ingredient;
    }

    public Ingredient getIngredientById(Long ingredientId) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        if (ingredient.isEmpty()) {
            throw new IngredientNotFoundException();
        }
        return ingredient.get();
    }

}
