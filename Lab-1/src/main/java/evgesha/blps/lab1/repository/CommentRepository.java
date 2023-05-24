package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByRecipeId(Long recipeId);
}
