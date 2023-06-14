package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.activemq.Producer;
import evgesha.blps.lab1.dto.TestDTO;
import evgesha.blps.lab1.entity.User;
import evgesha.blps.lab1.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
public class PingController {
    private final AuthenticationService authenticationService;

    private final Producer producer;

    public PingController(
            AuthenticationService authenticationService,
            Producer producer
    ) {
        this.authenticationService = authenticationService;
        this.producer = producer;
    }

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        System.out.println("/ping");
        Optional<User> isUser = authenticationService.getUserFromAuth();
        producer.sendMessage("inbound.queue", "{\"name\": \"BIBA\"}");
        producer.sendMessage("outbound.queue", "{\"name\": \"BIBA\"}");
        if (isUser.isEmpty()) {
            // АНОНИМ ПОГАНЫЙ
            return ResponseEntity.ok("Hello ANON from server Evgesha");
        }
        return ResponseEntity.ok("Hello " + isUser.get().getUsername() + " from server Evgesha");
    }

    @PostMapping(value = "testDTO")
    public ResponseEntity<?> testDTO(
            @Valid @RequestBody TestDTO testDto
    ) {
        System.out.println("/testDTO");
        return ResponseEntity.ok(testDto.getName());
    }

}
