package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.service.CommentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(
            CommentService commentService
    ) {
        this.commentService = commentService;
    }

//    @PostMapping("/comments/add")
//    public ResponseEntity<?> addCommentToRecipe(
//        @Valid CommentDto commentDto
//    ) {
//
//    }
}
