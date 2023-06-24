package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
