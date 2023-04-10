package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    public Tag getTagByName(String name);

}
