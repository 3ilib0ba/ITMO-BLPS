package evgesha.blps.lab1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopRecipeViewsListDto implements Serializable {
    @NotNull
    private List<RecipeViewsDto> topRecipesViews;
}
