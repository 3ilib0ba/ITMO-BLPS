package evgesha.blps.lab1.camunda;

import evgesha.blps.lab1.dto.CommentUserDto;
import evgesha.blps.lab1.dto.IngredientDto;
import evgesha.blps.lab1.dto.RecipeDto;
import evgesha.blps.lab1.service.RecipeService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.Int;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CamundaRecipesDelegate {

    @Autowired
    private RecipeService recipeService;

    @JobWorker(type = "post_recipe", autoComplete = true)
    public Map<String, Object> postRecipe(final JobClient client, final ActivatedJob job,
                                          @Variable String short_description,
                                          @Variable String heading,
                                          @Variable String author_comment,
                                          @Variable Integer cooking_time,
                                          @Variable Integer serving_number,
                                          @Variable String cuisine,
                                          @Variable String category,
                                          @Variable String target,
                                          @Variable String tag,
                                          @Variable Integer count,
                                          @Variable String name,
                                          @Variable String measure,
                                          @Variable Integer user_id
    ) {
        log.info("JOB: post recipe ");

        Map<String, Object> results = new HashMap<>();

        RecipeDto recipeDto = new RecipeDto(
                0,
                short_description,
                heading,
                Set.of(category),
                Set.of(target),
                Set.of(tag),
                List.of(new IngredientDto(count, name, measure, null)),
                author_comment,
                cooking_time,
                serving_number,
                cuisine,
                null,
                null,
                user_id
        );
        recipeService.addRecipe(recipeDto);

        results.put("new recipe", recipeDto.toString());

        return results;
    }

    @JobWorker(type = "get_recipe_by_name", autoComplete = true)
    public Map<String, Object> getRecipeByName(final JobClient client, final ActivatedJob job,
                                             @Variable String recipe_name
    ) {
        log.info("JOB: get recipe by name ");

        Map<String, Object> results = new HashMap<>();
        List<RecipeDto> recipes  = recipeService.getAllByName(recipe_name);
        StringBuilder recipesToString = new StringBuilder("");
        for (RecipeDto recipeUserDto : recipes) {
            recipesToString.append(recipeUserDto.toString()).append(" ");
        }
        results.put("recipes_by_name", recipesToString.toString());

        return results;
    }


    @JobWorker(type = "get_all_recipes", autoComplete = true)
    public Map<String, Object> getAllComments(final JobClient client, final ActivatedJob job) {

        log.info("JOB: get all recipes ");

        Map<String, Object> results = new HashMap<>();
        List<RecipeDto> recipes  = recipeService.getAllRecipes();
        StringBuilder recipesToString = new StringBuilder("");
        for (RecipeDto recipeUserDto : recipes) {
            recipesToString.append(recipeUserDto.toString()).append(" ");
        }
        results.put("all_recipes", recipesToString.toString());

        return results;
    }
}
