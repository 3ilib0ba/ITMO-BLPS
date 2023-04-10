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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    private final TagService tagService;

    private final RecipeMapper recipeMapper;

    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, TagService tagService, RecipeMapper recipeMapper, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.tagService = tagService;
        this.recipeMapper = recipeMapper;
        this.ingredientRepository = ingredientRepository;
    }


    public List<RecipeDto> getAllByName(String recipeName) {
        List<Recipe> results = recipeRepository.findByHeadingContainsIgnoreCase(recipeName);
        if (results.size() == 0) {
            throw new RecipeNotFoundException();
        }
        return results.stream().map(recipe -> recipeMapper.toDto(recipe)).collect(Collectors.toList());
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
                        .map(ingredientRepository::save)
                        .collect(Collectors.toList())
        );

        return new MessageDto(String.valueOf(recipeRepository.save(recipe).getId()));
    }

}
