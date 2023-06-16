package evgesha.blps.lab1.mapper;

import evgesha.blps.lab1.dto.RecipeViewsDto;
import evgesha.blps.lab1.dto.TopRecipeViewsListDto;
import evgesha.blps.lab1.entity.RecipeViews;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeViewsMapper {

    public TopRecipeViewsListDto toListDtoFromList(List<RecipeViews> recipeViews) {
        return new TopRecipeViewsListDto(
                recipeViews.stream()
                        .map(rv -> new RecipeViewsDto(rv.getId(), rv.getViewsCount()))
                        .collect(Collectors.toList()
                        )
        );
    }
}
