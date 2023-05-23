package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.dto.TestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        System.out.println("/ping");
        return ResponseEntity.ok("Hello from server Evgesha");
    }

    @PostMapping(value = "testDTO")
    public ResponseEntity<?> testDTO(
            @Valid @RequestBody TestDTO testDto
    ) {
        System.out.println("/testDTO");
        return ResponseEntity.ok(testDto.getName());
    }

}
