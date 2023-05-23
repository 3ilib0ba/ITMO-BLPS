package evgesha.blps.lab1.configuration;

import evgesha.blps.lab1.enums.Role;
import evgesha.blps.lab1.entity.User;
import evgesha.blps.lab1.repository.UserRepository;
import evgesha.blps.lab1.repository.XmlUserRepository;
import evgesha.blps.lab1.xml.XmlUserMarshaller;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User: %s, not found", username)));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public XmlUserMarshaller xml(){
        var xml = new XmlUserMarshaller();
        try {
            xml.readAllUsersFromXmlIntoMemory();
            return xml;
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public UserRepository userRepository(XmlUserMarshaller xml, PasswordEncoder passwordEncoder){
        var repo = new XmlUserRepository(xml);
        var users = repo.getAllUsers();
        if (users.isEmpty()){
            var admin = User.builder().id(1).enabled(true).role(Role.ADMIN).createdAt(new Date())
                    .password(passwordEncoder.encode("admin"))
                    .username("admin").build();
            repo.save(admin);
        } else {
            var maxId = users.stream().map(User::getId).max(Integer::compareTo).get();
            repo.setCurrentId(maxId+1);
        }
        return repo;
    }
}
