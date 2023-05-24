package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.dto.CommentDto;
import evgesha.blps.lab1.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(
            CommentService commentService
    ) {
        this.commentService = commentService;
    }

    @PostMapping("/comments/add")
    public ResponseEntity<?> addCommentToRecipe(
        @Valid CommentDto commentDto
    ) {
        return ResponseEntity.ok(commentService.postComment(commentDto));
    }
}
