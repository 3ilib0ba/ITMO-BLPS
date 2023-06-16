package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByRecipeIdAndUserId(Long recipeId, Integer userId);

    List<Like> findAllByRecipeIdAndIsLike(Long recipeId, boolean isLike);
}
