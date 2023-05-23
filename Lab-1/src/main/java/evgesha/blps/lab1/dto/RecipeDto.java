package evgesha.blps.lab1.dto;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {
    private long id;

    @JsonView
    @Length(min = 1, message = "too small short description")
    @NotNull(message = "short description must be not null")
    private String short_description;

    @JsonView
    @Length(min = 1, message = "too small heading")
    @NotNull(message = "heading must be not null")
    private String heading;

    @JsonView
    private Set<String> categories;

    @JsonView
    private Set<String> targets;

    @JsonView
    private Set<String> tags;

    @JsonView
    private List<IngredientDto> ingredients;

    @JsonView
    private String authorComment;

    @JsonView
    private Integer cookingTime;

    @JsonView
    private Integer servingNumber;

    @JsonView
    private String cuisine;

    @JsonView
    private String photo;

    @JsonView
    private String videoUrl;

    @JsonView
    private Integer userId;
}
