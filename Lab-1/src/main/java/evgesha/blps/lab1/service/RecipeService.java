package evgesha.blps.lab1.service;


import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.dto.RecipeDto;
import evgesha.blps.lab1.entity.Ingredient;
import evgesha.blps.lab1.entity.Recipe;
import evgesha.blps.lab1.exception.RecipeNotFoundException;
import evgesha.blps.lab1.mapper.RecipeMapper;
import evgesha.blps.lab1.repository.IngredientRepository;
import evgesha.blps.lab1.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    private final TagService tagService;

    private final RecipeMapper recipeMapper;

    private final IngredientService ingredientService;

    public RecipeService(
            RecipeRepository recipeRepository,
            TagService tagService,
            RecipeMapper recipeMapper,
            IngredientService ingredientService
    ) {
        this.recipeRepository = recipeRepository;
        this.tagService = tagService;
        this.recipeMapper = recipeMapper;
        this.ingredientService = ingredientService;
    }


    public List<RecipeDto> getAllByName(String recipeName) {
        List<Recipe> results = recipeRepository.findByHeadingContainsIgnoreCase(recipeName);
        if (results.size() == 0) {
            return new ArrayList<>();
        }
        return results.stream().map(recipeMapper::toDto).collect(Collectors.toList());
    }

    public MessageDto addRecipe(RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.fromDto(recipeDto);

        recipe.setTags(
                recipe.getTags().stream()
                        .map(tag -> tagService.checkTagAndAddIfNoExist(tag.getName()))
                        .collect(Collectors.toList())
        );
        recipe.setIngredients(
                recipe.getIngredients().stream()
                        .map(ingredientService::checkIngredientForMeasureAndSave)
                        .collect(Collectors.toList())
        );

        return new MessageDto(String.valueOf(recipeRepository.save(recipe).getId()));
    }

}
