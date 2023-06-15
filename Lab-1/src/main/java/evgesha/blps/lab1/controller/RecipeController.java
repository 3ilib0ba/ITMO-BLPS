package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.dto.RecipeDto;
import evgesha.blps.lab1.service.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@RestController
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes/getById")
    public ResponseEntity<?> getCurrentRecipeByIdWithComments(
            @RequestParam(name = "recipeId")
            @NotNull Long recipeId
    ) {
        return ResponseEntity.ok(recipeService.getRecipeByIdWithComments(recipeId));
    }

    @GetMapping("/recipes/getByName")
    public ResponseEntity<?> getListOfRecipesByMealName(
            @RequestParam(name = "recipeName")
            @NotBlank
            String recipeName
    ) {
        return ResponseEntity.ok(recipeService.getAllByName(recipeName));
    }

    @GetMapping("/recipes/getAll")
    public ResponseEntity<?> getAllRecipes(
    ) {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @PostMapping(value = "/recipes/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRecipe(
            @Valid @RequestBody RecipeDto recipeDto
    ) {
        return ResponseEntity.ok(recipeService.addRecipe(recipeDto));
    }

    @DeleteMapping("/recipes/recipe")
    public ResponseEntity<?> deleteRecipeById(
            @RequestParam Long recipeId
    ) {
        return ResponseEntity.ok(recipeService.deleteRecipeAndReturnIt(recipeId));
    }


}
