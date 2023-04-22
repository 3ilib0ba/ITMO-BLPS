package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByHeadingContainsIgnoreCase(String heading);

    List<Recipe> findAll();

}
