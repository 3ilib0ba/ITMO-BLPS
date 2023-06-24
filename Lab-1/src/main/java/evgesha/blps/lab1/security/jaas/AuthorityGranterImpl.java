package evgesha.blps.lab1.security.jaas;


import evgesha.blps.lab1.entity.User;
import evgesha.blps.lab1.exception.UserNotFoundException;
import evgesha.blps.lab1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.jaas.AuthorityGranter;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class AuthorityGranterImpl implements AuthorityGranter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorityGranterImpl.class);
    private final UserRepository userRepository;
    @Override
    public Set<String> grant(Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isPresent()) {
            return Collections.singleton(user.get().getRole().name());
        }

        // TODO исправить на новую ошибку USER_CREDITIONS_ERROR
        throw new UserNotFoundException();
    }
}
