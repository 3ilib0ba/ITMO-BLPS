package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.User;
import evgesha.blps.lab1.xml.XmlUserMarshaller;
import lombok.Setter;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Setter
public class XmlUserRepository implements UserRepository {
    private final XmlUserMarshaller xml;
    private Integer currentId;
    @Override
    public Optional<User> findByUsername(String username) {
        var users = xml.getUsers();

        if (users == null || users.isEmpty()) return Optional.empty();
        return users.stream().filter(u -> username.equals(u.getUsername())).findFirst();
    }

    @Override
    public void save(User user) {
        try {
            var users = xml.getUsers();
            boolean update = user.getId() != null && users.stream().anyMatch(u -> Objects.equals(u.getId(), user.getId()));
            if (update) {
                users.removeIf(u -> Objects.equals(u.getId(), user.getId()));
                users.add(user);
            } else {
                currentId++;
                user.setId(currentId);
                users.add(user);
            }
            xml.writeUsersToXml(users);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        return xml.getUsers().stream().filter(u -> Objects.equals(u.getId(), id)).findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return xml.getUsers();
    }

    public XmlUserRepository(XmlUserMarshaller xml) {
        this.xml = xml;
        this.currentId = 0;
    }
}
