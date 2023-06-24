package evgesha.blps.lab1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "recipe_count_views")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecipeViews {
    @Id
    @Column(name = "recipe_id", nullable = false)
    private Long id;

    @Column(name = "count_of_views", nullable = false)
    @Min(0)
    private int viewsCount;
}
