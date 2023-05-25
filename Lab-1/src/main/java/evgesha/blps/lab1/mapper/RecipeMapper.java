package evgesha.blps.lab1.mapper;


import evgesha.blps.lab1.dto.CommentUserDto;
import evgesha.blps.lab1.dto.CurrentRecipeCommentsDto;
import evgesha.blps.lab1.dto.RecipeDto;
import evgesha.blps.lab1.entity.*;
import evgesha.blps.lab1.exception.UserNotFoundException;
import evgesha.blps.lab1.repository.CategoryRepository;
import evgesha.blps.lab1.repository.CommentRepository;
import evgesha.blps.lab1.repository.TargetRepository;
import evgesha.blps.lab1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private IngredientMapper ingredientMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

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
                recipe.getVideoUrl(),
                recipe.getUserId()
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

        Optional<User> ownerOpt = userRepository.findById(recipeDto.getUserId());
        if (ownerOpt.isEmpty()) {
            throw new UserNotFoundException();
        }


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
                recipeDto.getVideoUrl(),
                ownerOpt.get().getId()
        );
    }

    public CurrentRecipeCommentsDto toDtoWithComments(Recipe recipe) {
        List<Comment> comments = commentRepository.findAllByRecipeId(recipe.getId());
        List<CommentUserDto> commentsUsers = commentMapper.toDto(comments);

        return new CurrentRecipeCommentsDto(
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
                recipe.getVideoUrl(),
                recipe.getUserId(),
                commentsUsers
        );
    }

}
