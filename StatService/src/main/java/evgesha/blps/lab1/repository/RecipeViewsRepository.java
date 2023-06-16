package evgesha.blps.lab1.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import evgesha.blps.lab1.entity.RecipeViews;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface RecipeViewsRepository extends JpaRepository<RecipeViews, Long> {

    RecipeViews findById(Integer id);

//    RecipeViews findTopByViewsCount();

}
