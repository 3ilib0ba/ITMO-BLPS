package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.dto.RecipeDto;
import evgesha.blps.lab1.dto.TestDTO;
import evgesha.blps.lab1.service.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getCurrentRecipeByIdWithComments(
            @RequestParam(name = "recipeId")
            @NotNull Long recipeId
    ) {
        return ResponseEntity.ok(recipeService.getRecipeByIdWithComments(recipeId));
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

    @PostMapping(
            value = "/add",
            consumes = {
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
//            consumes = {"*/*"}
    )
    public ResponseEntity<?> addRecipe(
            @Valid RecipeDto recipeDto,
            @RequestParam MultipartFile image

    ) {
        System.out.println(recipeDto);
        return ResponseEntity.ok(recipeService.addRecipe(recipeDto, image));
    }


    @PostMapping(
            value = "/addTEST",
            consumes = {
                    MediaType.MULTIPART_FORM_DATA_VALUE,
                    "application/json"
            }
    )
    public ResponseEntity<?> addRecipeTEST(
            @Valid TestDTO testDTO,
            @RequestParam MultipartFile image

    ) {
        System.out.println(testDTO.getName());
        return ResponseEntity.ok(testDTO + " " + image.getName());
    }


}
