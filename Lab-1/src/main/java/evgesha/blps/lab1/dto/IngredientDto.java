package evgesha.blps.lab1.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
    @NotNull
    @Min(value = 1, message = "set a count more than zero")
    private int count;

    @NotBlank
    private String name;

    @NotBlank
    private String measure;
}
