package evgesha.blps.lab1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeWithViewsDto {
    private RecipeDto recipeDto;

    private int views;
}
