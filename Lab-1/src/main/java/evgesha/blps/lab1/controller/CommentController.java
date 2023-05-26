package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.dto.CommentDto;
import evgesha.blps.lab1.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(
            CommentService commentService
    ) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/getAll")
    public ResponseEntity<?> getAllComments(
    ) {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/comments/getById")
    public ResponseEntity<?> getCommentById(
            @RequestParam Long commentId
    ) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @PostMapping("/comments/add")
    public ResponseEntity<?> addCommentToRecipe(
        @Valid CommentDto commentDto
    ) {
        return ResponseEntity.ok(commentService.postComment(commentDto));
    }

    @DeleteMapping("/comments/delete")
    public ResponseEntity<?> deleteCommentById(
            @RequestParam Long commentId
    ) {
        return ResponseEntity.ok(commentService.returnDeletedComment(commentId));
    }

}
