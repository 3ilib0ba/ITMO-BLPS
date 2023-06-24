package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository extends JpaRepository<Target, Long> {

    public Target getTargetByName(String name);

}
