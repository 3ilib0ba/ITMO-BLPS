package evgesha.blps.lab1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "recipe_likes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(LikeCompositeKey.class)
public class Like {
    @Id
    @Column(name = "recipe_id", nullable = false)
    private long recipeId;
    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "is_like")
    private Boolean isLike;
}
