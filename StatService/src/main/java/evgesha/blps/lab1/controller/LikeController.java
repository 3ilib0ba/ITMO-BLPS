package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.service.RecipeLikesService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class LikeController {

    private final RecipeLikesService recipeLikesService;

    public LikeController(RecipeLikesService recipeLikesService) {
        this.recipeLikesService = recipeLikesService;
    }

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        System.out.println("/ping");
        return ResponseEntity.ok("Hello from server Evgesha");
    }
    @GetMapping("/getCountOfLike")
    public ResponseEntity<?> getCountOfLikes(@RequestParam Long recipeId) {
        return ResponseEntity.ok(recipeLikesService.getCountOfLike(recipeId));
    }
}
