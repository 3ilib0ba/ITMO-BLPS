package evgesha.blps.lab1.camunda;

import evgesha.blps.lab1.entity.User;
import evgesha.blps.lab1.exception.UserNotFoundException;
import evgesha.blps.lab1.repository.UserRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import io.camunda.zeebe.spring.client.exception.ZeebeBpmnError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthByCamunda {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @JobWorker(type = "user_auth", autoComplete = true)
    public Map<String, Object> loginAndGetUser(final JobClient client, final ActivatedJob job,
                                               @Variable String login,
                                               @Variable String password
    ) {
        Optional<User> user = userRepository.findByUsername(login);
        Integer user_id = -1;
        if (user.isPresent()) {
            boolean passwordIsCorrect = passwordEncoder.matches(password, user.get().getPassword());
            if (passwordIsCorrect) {
                log.info("login {} password {}", login, password);
                user_id = user.get().getId();
            } else {
                throw new ZeebeBpmnError("DOESNT_WORK", "Bad login/password");
            }
        } else {
            throw new ZeebeBpmnError("DOESNT_WORK", "User does not exist");
        }
        log.info("JOB: user_auth");
        log.info("user_id {}", user_id);
        return Map.of("user_id", user_id);
    }
}
