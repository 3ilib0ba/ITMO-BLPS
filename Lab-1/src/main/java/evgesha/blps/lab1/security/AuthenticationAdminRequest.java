package evgesha.blps.lab1.security;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AuthenticationAdminRequest {
        @JsonView
        @Length(min = 3, max = 50, message = "Слишком короткий или длинный никнейм администратора")
        @NotNull(message = "Никнейм не должен быть пустым")
        private String name;

        @JsonView
        @Size(min = 6, max = 32, message = "Слишком короткий или длинный пароль")
        @NotNull(message = "Пароль не должен быть пустым")
        private String password;
}
