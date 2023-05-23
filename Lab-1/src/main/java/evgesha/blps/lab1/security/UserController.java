package evgesha.blps.lab1.security;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/data")
    @PreAuthorize("hasRole('USER')")
    public String getUserData() {
        // выполнение операции бизнес-логики для пользователя
        return "User data";
    }

}
