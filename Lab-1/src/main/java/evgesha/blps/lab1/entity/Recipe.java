package evgesha.blps.lab1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recipe {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "short_description", nullable = false)
    private String short_description;

    @Column(name = "heading", nullable = false)
    private String heading;

    @ManyToMany
    @JoinTable(
            name = "recipe_category_relation",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "recipe_target_relation",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "target_id")}
    )
    private List<Target> targets;

    @ManyToMany
    @JoinTable(
            name = "recipe_tags_relation",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "tags_id")}
    )
    private List<Tag> tags;

    @ManyToMany
    @JoinTable(
            name = "recipe_ingredient_relation",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")}
    )
    private List<Ingredient> ingredients;

    @Column(name = "author_comment")
    private String authorComment;

    @Column(name = "cooking_time")
    private int cookingTime;

    @Column(name = "serving_number")
    private int servingNumber;

    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "main_photo")
    private String photo;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", short_description='" + short_description + '\'' +
                ", heading='" + heading + '\'' +
                ", categories=" + categories +
                ", targets=" + targets +
                ", tags=" + tags +
                ", ingredients=" + ingredients +
                ", authorComment='" + authorComment + '\'' +
                ", cookingTime=" + cookingTime +
                ", servingTime=" + servingNumber +
                ", cuisine='" + cuisine + '\'' +
                ", photo=" + photo +
                ", videoUrl='" + videoUrl + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
