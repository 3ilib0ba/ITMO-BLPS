package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public Optional<User> findByUsername(String username);
    void save(User user);

    Optional<User> findById(Integer id);

    List<User> getAllUsers();
}
