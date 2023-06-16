package evgesha.blps.lab1.service;


import evgesha.blps.lab1.broker.MqttPublisher;
import evgesha.blps.lab1.dto.*;
import evgesha.blps.lab1.entity.*;
import evgesha.blps.lab1.exception.RecipeNotFoundException;
import evgesha.blps.lab1.mapper.RecipeMapper;
import evgesha.blps.lab1.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    @Value("${topic.stat_count_views}")
    private String STAT_COUNT_VIEWS_TOPIC;

    @Value("${topic.email_recipe_mail_sender}")
    private String EMAIL_RECIPE_MAIL_SENDER;

    @Value("${topic.stat_likes_recipes}")
    private String STAT_LIKES_RECIPES;

    private final MqttPublisher mqttPublisher;

    private final AuthenticationService authenticationService;

    private final RecipeRepository recipeRepository;

    private final TagService tagService;

    private final RecipeMapper recipeMapper;

    private final IngredientService ingredientService;

    private final CommentService commentService;

    private final ImageService imageService;

    public RecipeService(
            MqttPublisher mqttPublisher,
            AuthenticationService authenticationService,
            RecipeRepository recipeRepository,
            TagService tagService,
            RecipeMapper recipeMapper,
            IngredientService ingredientService,
            CommentService commentService,
            ImageService imageService
    ) {
        this.mqttPublisher = mqttPublisher;
        this.authenticationService = authenticationService;
        this.recipeRepository = recipeRepository;
        this.tagService = tagService;
        this.recipeMapper = recipeMapper;
        this.ingredientService = ingredientService;
        this.commentService = commentService;
        this.imageService = imageService;
    }


    public List<RecipeDto> getAllByName(String recipeName) {
        List<Recipe> results = recipeRepository.findByHeadingContainsIgnoreCase(recipeName);
        if (results.size() == 0) {
            return new ArrayList<>();
        }
        return results.stream().map(recipeMapper::toDto).collect(Collectors.toList());
    }

    public List<RecipeDto> getAllRecipes() {
        List<Recipe> results = recipeRepository.findAll();
        if (results.size() == 0) {
            return new ArrayList<>();
        }
        return results.stream().map(recipeMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
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

        Recipe result = recipeRepository.save(recipe);
        result.getIngredients()
                .forEach(ingredient -> ingredientService.setRecipeAfterAdded(ingredient, result));

        return new MessageDto(String.valueOf(result.getId()));
    }

    public CurrentRecipeCommentsDto getRecipeByIdWithComments(Long recipeId) {
        Recipe recipe = getRecipeById(recipeId);

        mqttPublisher.publishToTopic(STAT_COUNT_VIEWS_TOPIC, String.valueOf(recipeId));

        return recipeMapper.toDtoWithComments(recipe);
    }

    @Transactional
    public CurrentRecipeCommentsDto deleteRecipeAndReturnIt(Long recipeId) {
        CurrentRecipeCommentsDto result =
                recipeMapper.toDtoWithComments(getRecipeById(recipeId));

        deleteRecipeById(recipeId);

        return result;
    }

    public void deleteRecipeById(Long recipeId) {
        Recipe recipe = getRecipeById(recipeId);

        List<Comment> deletedComments = commentService.deleteAllCommentsByRecipe(recipe);
        String imageName = recipe.getPhoto();
        if (imageName != null) {
            ImageDto deletedImage = imageService.deleteImageByName(recipe.getPhoto());
        }
        List<Ingredient> deletedIngredients = ingredientService.deleteIngredients(recipe.getIngredients());

        recipeRepository.delete(recipe);
    }

    public EmailWithRecipeDto sendRecipeByEmail(Long recipeId, String email) {
        Recipe recipe = getRecipeById(recipeId);
        EmailWithRecipeDto result = new EmailWithRecipeDto(recipeMapper.toDto(recipe), email);

        mqttPublisher.publishToTopic(EMAIL_RECIPE_MAIL_SENDER, result);

        return result;
    }

    public LikeDto putLikeToRecipe(Long recipeId) {
        User user = authenticationService.getAndCheckRealUser();
        Recipe recipe = getRecipeById(recipeId);

        LikeDto likeInfo = new LikeDto(recipeId, user.getId());
        mqttPublisher.publishToTopic(STAT_LIKES_RECIPES, likeInfo);

        return likeInfo;
    }

    public Recipe getRecipeById(Long recipeId) {
        Optional<Recipe> isRecipe = recipeRepository.findById(recipeId);
        if (isRecipe.isEmpty()) {
            throw new RecipeNotFoundException();
        }
        return isRecipe.get();
    }


}
