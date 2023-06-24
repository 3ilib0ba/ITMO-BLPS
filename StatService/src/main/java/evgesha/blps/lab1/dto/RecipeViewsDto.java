package evgesha.blps.lab1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeViewsDto implements Serializable {
    @NotNull
    private Long id;

    private int viewsCount;
}
