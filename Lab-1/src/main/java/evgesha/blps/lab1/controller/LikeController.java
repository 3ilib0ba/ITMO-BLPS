package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@Validated
public class LikeController {
    private final RecipeService recipeService;

    public LikeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/likes/setLike")
    public ResponseEntity<?> setLikeToRecipe(
            @RequestParam(name = "recipeId")
            @NotNull
            Long recipeId
    ) {
        return ResponseEntity.ok(recipeService.putLikeToRecipe(recipeId));
    }




}
