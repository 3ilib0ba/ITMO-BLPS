package evgesha.blps.lab1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        System.out.println("/ping");
        return ResponseEntity.ok("Hello from server Evgesha");
    }

}
