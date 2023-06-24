package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.RecipeViews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeViewsRepository extends JpaRepository<RecipeViews, Long> {
    RecipeViews findById(Integer id);

    List<RecipeViews> findTop3ByOrderByViewsCountDesc();

}
