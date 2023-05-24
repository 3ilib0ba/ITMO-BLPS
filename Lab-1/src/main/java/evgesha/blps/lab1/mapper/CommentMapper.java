package evgesha.blps.lab1.mapper;

import evgesha.blps.lab1.dto.CommentDto;
import evgesha.blps.lab1.dto.CommentUserDto;
import evgesha.blps.lab1.entity.Comment;
import evgesha.blps.lab1.entity.Recipe;
import evgesha.blps.lab1.entity.User;
import evgesha.blps.lab1.exception.RecipeNotFoundException;
import evgesha.blps.lab1.exception.UserNotFoundException;
import evgesha.blps.lab1.repository.RecipeRepository;
import evgesha.blps.lab1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentMapper {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;


    private final String ANONYMOUS_AUTHOR = "anonymous";

    public Comment fromDtoAndUser(CommentDto commentDto, Optional<User> isUser) {
        Optional<Recipe> isRecipe = recipeRepository.findById(commentDto.getRecipeId());
        if (isRecipe.isEmpty()) {
            throw new RecipeNotFoundException();
        }

        if (isUser.isEmpty()) {
            return new Comment(0, commentDto.getText(), isRecipe.get(), null);
        }
        return new Comment(0, commentDto.getText(), isRecipe.get(), isUser.get().getId());
    }

    public CommentUserDto toDto(Comment comment) {
        if (comment.getUserId() == null) {
            return new CommentUserDto(
                    comment.getId(),
                    comment.getText(),
                    comment.getRecipe().getId(),
                    ANONYMOUS_AUTHOR);
        }

        Optional<User> isUser = userRepository.findById(comment.getUserId());
        if (isUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = isUser.get();
        return new CommentUserDto(
                comment.getId(),
                comment.getText(),
                comment.getRecipe().getId(),
                user.getUsername());
    }
}
