package evgesha.blps.lab1.dto;


import evgesha.blps.lab1.entity.Category;
import evgesha.blps.lab1.entity.Ingredient;
import evgesha.blps.lab1.entity.Tag;
import evgesha.blps.lab1.entity.Target;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {
    @Null(message = "Id must not be set")
    private long id;

    @Length(min = 1, message = "too small short description")
    @NotNull(message = "short description must be not null")
    private String short_description;

    @Length(min = 1, message = "too small heading")
    @NotNull(message = "heading must be not null")
    private String heading;

    private Set<String> categories;

    private Set<String> targets;

    private Set<String> tags;

    private List<IngredientDto> ingredients;

    private String authorComment;

    private Integer cookingTime;

    private Integer servingNumber;

    private String cuisine;

    private String photo;

    private String videoUrl;
}
