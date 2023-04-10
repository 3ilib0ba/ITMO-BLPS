package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepository extends JpaRepository<Measure, Integer> {

    public Measure getMeasuresByName(String name);

}
