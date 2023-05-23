package evgesha.blps.lab1.security;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @JsonView
    @Size(min = 3, max = 50, message = "Слишком короткий или длинный никнейм")
    @NotBlank(message = "Никнейм не должен быть пустым")
    private String name;

    @JsonView
    @Size(min = 5, max = 32, message = "Слишком короткий или длинный пароль")
    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;
}
