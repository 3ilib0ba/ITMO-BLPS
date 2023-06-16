package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByRecipeIdAndUserId(Long recipeId, Integer userId);

    @Query("select count(l) from Like l where l.isLike = true and l.recipeId = ?1")
    Long getCountOfLikes(Long recipeId);
}
