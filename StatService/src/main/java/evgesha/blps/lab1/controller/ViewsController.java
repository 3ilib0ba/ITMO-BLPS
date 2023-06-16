package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.service.RecipeViewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Validated
@RestController
public class ViewsController {

    private final RecipeViewsService recipeViewsService;


    public ViewsController(RecipeViewsService recipeViewsService) {
        this.recipeViewsService = recipeViewsService;
    }

    @GetMapping("/views")
    public ResponseEntity<?> getViews(
            @RequestParam(name = "recipeId")
            @NotNull Long recipeId
    ) {
        return ResponseEntity.ok(recipeViewsService.getViews(recipeId));
    }

//    @GetMapping("/topviews")
//    public ResponseEntity<?> getTopViews() {
//        return ResponseEntity.ok(recipeViewsService.getTopViews());
//    }

}
