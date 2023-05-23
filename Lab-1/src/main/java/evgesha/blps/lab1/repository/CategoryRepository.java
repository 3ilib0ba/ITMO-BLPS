package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category getCategoryByName(String name);
}
