package evgesha.blps.lab1.mapper;

import evgesha.blps.lab1.dto.IngredientDto;
import evgesha.blps.lab1.entity.Ingredient;
import evgesha.blps.lab1.entity.Recipe;
import evgesha.blps.lab1.exception.RecipeNotFoundException;
import evgesha.blps.lab1.repository.IngredientRepository;
import evgesha.blps.lab1.repository.MeasureRepository;
import evgesha.blps.lab1.repository.RecipeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {
    final IngredientRepository ingredientRepository;

    final MeasureRepository measureRepository;

    final RecipeRepository recipeRepository;

    public IngredientMapper(
            IngredientRepository ingredientRepository,
            MeasureRepository measureRepository,
            RecipeRepository recipeRepository
    ) {
        this.ingredientRepository = ingredientRepository;
        this.measureRepository = measureRepository;
        this.recipeRepository = recipeRepository;
    }

    public IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getCount(),
                ingredient.getName(),
                ingredient.getMeasure().getName(),
                ingredient.getRecipe().getId()
        );
    }

    public List<IngredientDto> toDtoFromList(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Ingredient fromDto(IngredientDto ingredientDto) {
        if (ingredientDto.getRecipeId() == null) {
            return new Ingredient(
                    0,
                    ingredientDto.getCount(),
                    ingredientDto.getName(),
                    measureRepository.getMeasuresByName(ingredientDto.getMeasure()),
                    null
            );
        }

        Optional<Recipe> recipe = recipeRepository.findById(ingredientDto.getRecipeId());
        if (recipe.isEmpty()) {
            throw new RecipeNotFoundException();
        }

        return new Ingredient(
                0,
                ingredientDto.getCount(),
                ingredientDto.getName(),
                measureRepository.getMeasuresByName(ingredientDto.getMeasure()),
                recipe.get()
        );
    }

    public List<Ingredient> fromDtoFromList(List<IngredientDto> ingredientDtos) {
        return ingredientDtos.stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

}
