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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuthenticationService authenticationService;

    private final Set<String> notAllowedWords = new HashSet<>(){{
        add("POPA");
        add("KAKA");
        add("FUCK");
        add("БЕСТОЛОЧЬ");
        add("ДУРАК");
    }};

    public CommentService(
            CommentRepository commentRepository,
            CommentMapper commentMapper,
            AuthenticationService authenticationService
    ) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.authenticationService = authenticationService;
    }

    public List<Comment> deleteAllCommentsWithBadWords() {
        List<Comment> allComments = commentRepository.findAll();
        List<Long> idsToDelete = allComments.stream()
                .filter(comment -> commentHasBadWords(comment))
                .map(comment -> comment.getId())
                .toList();

        return deleteCommentsByIds(idsToDelete);
    }

    private boolean commentHasBadWords(Comment comment) {
        String text = comment.getText();
        for (String badWord : notAllowedWords) {
            if (text.contains(badWord)) {
                return true;
            }
        }

        return false;
    }

    public List<CommentUserDto> getAllComments() {
        return commentMapper.toDto(commentRepository.findAll());
    }

    @Transactional
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

    @Transactional
    public CommentUserDto returnDeletedComment(Long commentId) {
        Comment comment = deleteCommentById(commentId);
        return commentMapper.toDto(comment);
    }

    public List<Comment> deleteCommentsByIds(List<Long> ids) {
        return ids.stream()
                .map(id -> deleteCommentById(id))
                .toList();
    }

    @Transactional
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
