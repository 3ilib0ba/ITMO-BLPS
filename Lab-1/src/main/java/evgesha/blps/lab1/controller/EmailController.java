package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@RestController
public class EmailController {
    private final RecipeService recipeService;

    public EmailController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/emails/sendRecipe")
    public ResponseEntity<?> sendRecipeToEmail(
            @RequestParam(name = "recipeId")
            @NotNull Long recipeId,
            @RequestParam(name = "email")
            @NotBlank String email
    ) {
        return ResponseEntity.ok(recipeService.sendRecipeByEmail(recipeId, email));
    }


}