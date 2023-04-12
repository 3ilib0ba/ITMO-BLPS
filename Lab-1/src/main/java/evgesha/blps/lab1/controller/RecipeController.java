package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.dto.RecipeDto;
import evgesha.blps.lab1.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/getByName")
    public ResponseEntity<?> getListOfRecipesByMealName(
            @RequestParam(name = "recipeName")
            @NotBlank
            String recipeName
    ) {
        return ResponseEntity.ok(recipeService.getAllByName(recipeName));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRecipe(
            @RequestBody RecipeDto recipeDto
            ) {
        return ResponseEntity.ok(recipeService.addRecipe(recipeDto));
    }

}
