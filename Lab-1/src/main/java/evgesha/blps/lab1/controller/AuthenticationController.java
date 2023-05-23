package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.security.AuthenticationAdminRequest;
import evgesha.blps.lab1.security.RegisterRequest;
import evgesha.blps.lab1.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Hidden
    @PostMapping(value = "/admin/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerAdmin(
            @Valid @RequestBody AuthenticationAdminRequest request
    ) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }
}
