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
public class RegisterRequest {
    @JsonView
    @NotBlank(message = "Никнейм не должен быть пустым")
    @Size(min = 3, max = 50, message = "Слишком короткий или длинный никнейм")
    private String name;

    @JsonView
    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 6, max = 32, message = "Слишком короткий или длинный пароль")
    private String password;
}
