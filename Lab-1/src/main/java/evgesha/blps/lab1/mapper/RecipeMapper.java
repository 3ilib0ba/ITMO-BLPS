package evgesha.blps.lab1.mapper;


import evgesha.blps.lab1.dto.RecipeDto;
import evgesha.blps.lab1.entity.*;
import evgesha.blps.lab1.repository.CategoryRepository;
import evgesha.blps.lab1.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private IngredientMapper ingredientMapper;

    public RecipeDto toDto(Recipe recipe) {
        return new RecipeDto(
                recipe.getId(),
                recipe.getShort_description(),
                recipe.getHeading(),
                recipe.getCategories().stream().map(Category::getName).collect(Collectors.toSet()),
                recipe.getTargets().stream().map(Target::getName).collect(Collectors.toSet()),
                recipe.getTags().stream().map(Tag::getName).collect(Collectors.toSet()),
                ingredientMapper.toDtoFromList(recipe.getIngredients()),
                recipe.getAuthorComment(),
                recipe.getCookingTime(),
                recipe.getServingNumber(),
                recipe.getCuisine(),
                recipe.getPhoto(),
                recipe.getVideoUrl()
        );
    }

    public Recipe fromDto(RecipeDto recipeDto) {
        // TODO Возврат любых категорий -- ОК
        //      Проверка на то, что категории должны быть из базы -- в сервисе
        List<Category> categories = recipeDto.getCategories().stream()
                .map(categoryRepository::getCategoryByName)
                .filter(Objects::nonNull)
                .toList();

        List<Target> targets = recipeDto.getTargets().stream()
                .map(targetRepository::getTargetByName)
                .filter(Objects::nonNull)
                .toList();

        List<Tag> tags = recipeDto.getTags().stream()
                .map(tagName -> new Tag(0, tagName))
                .toList();

        List<Ingredient> ingredients = ingredientMapper.fromDtoFromList(recipeDto.getIngredients());


        return new Recipe(
                recipeDto.getId(),
                recipeDto.getShort_description(),
                recipeDto.getHeading(),
                categories,
                targets,
                tags,
                ingredients,
                recipeDto.getAuthorComment(),
                recipeDto.getCookingTime(),
                recipeDto.getServingNumber(),
                recipeDto.getCuisine(),
                recipeDto.getPhoto(),
                recipeDto.getVideoUrl()
        );
    }

}
