package evgesha.blps.lab1.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailWithRecipeDto implements Serializable {
    @JsonView
    private RecipeDto recipeDto;

    @JsonView
    private String email;
}
