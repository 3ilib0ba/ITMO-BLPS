package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.dto.RecipeDto;
import evgesha.blps.lab1.service.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllRecipes(
    ) {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @PostMapping(value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRecipe(
            @Valid @RequestBody RecipeDto recipeDto,
            @RequestParam("file") MultipartFile image

    ) {
        return ResponseEntity.ok(recipeService.addRecipe(recipeDto, image));
    }

}
