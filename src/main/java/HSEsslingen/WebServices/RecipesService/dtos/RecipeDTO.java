package HSEsslingen.WebServices.RecipesService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "recipes")
@JsonFilter(value = "recipeFilter")
@Data
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class RecipeDTO extends RepresentationModel<RecipeDTO> {

    private String id;
    private String title;
    private String subTitle;
    private String description;
    private String category;
    private Integer servings;
    private Double calories;
    private String levelOfDifficulty;
    private Long workingTimeInSeconds;
    private Long cookingTimeInSeconds;
    private Long restingTimeInSeconds;
    private List<String> images;
    private List<String> ingredients;
    
}
