package evgesha.blps.lab1.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public String addUser(@RequestBody User user) {
        // выполнение операции бизнес-логики для администратора
        return "User added";
    }

}
