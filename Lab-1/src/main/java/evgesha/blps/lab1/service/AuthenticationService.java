package evgesha.blps.lab1.service;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.entity.User;
import evgesha.blps.lab1.enums.Role;
import evgesha.blps.lab1.exception.UserAlreadyExistException;
import evgesha.blps.lab1.repository.UserRepository;
import evgesha.blps.lab1.security.AuthenticationAdminRequest;
import evgesha.blps.lab1.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public MessageDto register(RegisterRequest request) {
        if (repository.findByUsername(request.getName()).isPresent()){
            throw new UserAlreadyExistException();
        }

        var user = User.builder()
                .username(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(new Date())
                .enabled(true)
                .build();

        repository.save(user);
        return new MessageDto("Registered");
    }

    public MessageDto registerAdmin(AuthenticationAdminRequest adminRequest) {
        if (repository.findByUsername(adminRequest.getName()).isPresent()){
            throw new UserAlreadyExistException();
        }
        var user = User.builder()
                .username(adminRequest.getName())
                .password(passwordEncoder.encode(adminRequest.getPassword()))
                .createdAt(new Date())
                .role(Role.ADMIN)
                .enabled(true)
                .build();
        repository.save(user);
        return new MessageDto("Registered");
    }
}

