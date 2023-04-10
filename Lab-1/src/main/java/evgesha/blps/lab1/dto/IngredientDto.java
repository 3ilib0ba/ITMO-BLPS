package evgesha.blps.lab1.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
