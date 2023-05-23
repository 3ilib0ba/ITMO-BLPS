package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
