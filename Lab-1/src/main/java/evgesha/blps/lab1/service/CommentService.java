package evgesha.blps.lab1.service;

import evgesha.blps.lab1.dto.CommentDto;
import evgesha.blps.lab1.dto.CommentUserDto;
import evgesha.blps.lab1.entity.Comment;
import evgesha.blps.lab1.entity.Recipe;
import evgesha.blps.lab1.entity.User;
import evgesha.blps.lab1.exception.CommentNotFoundException;
import evgesha.blps.lab1.mapper.CommentMapper;
import evgesha.blps.lab1.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuthenticationService authenticationService;

    public CommentService(
            CommentRepository commentRepository,
            CommentMapper commentMapper,
            AuthenticationService authenticationService
    ) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.authenticationService = authenticationService;
    }

    public List<CommentUserDto> getAllComments() {
        return commentMapper.toDto(commentRepository.findAll());
    }

    public CommentUserDto postComment(CommentDto commentDto) {
        Optional<User> isUser = authenticationService.getUserFromAuth();

        Comment comment = commentMapper.fromDtoAndUser(commentDto, isUser);
        Comment result = commentRepository.save(comment);

        return commentMapper.toDto(result);
    }

    public List<Comment> deleteAllCommentsByRecipe(Recipe recipe) {
        return commentRepository
                .findAllByRecipeId(recipe.getId()).stream()
                .peek(commentRepository::delete)
                .collect(Collectors.toList());
    }

    public CommentUserDto returnDeletedComment(Long commentId) {
        Comment comment = deleteCommentById(commentId);
        return commentMapper.toDto(comment);
    }

    public Comment deleteCommentById(Long commentId) {
        Comment deleted = getCommentById(commentId);
        commentRepository.delete(deleted);
        return deleted;
    }

    public Comment getCommentById(Long commentId) {
        Optional<Comment> isComment = commentRepository.findById(commentId);
        if (isComment.isEmpty()) {
            throw new CommentNotFoundException();
        }
        return isComment.get();
    }
}
