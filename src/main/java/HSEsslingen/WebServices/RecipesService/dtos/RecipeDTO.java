package HSEsslingen.WebServices.RecipesService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "recipes")
@Data
@AllArgsConstructor
public class RecipeDTO extends RepresentationModel<RecipeDTO> {

    private String id;
    private String title;
    private String subTitle;
    private String description;
    private String category;
    private Integer servings;
    private Double calories;
    private String levelOfDifficulty;
    private long workingTimeInSeconds;
    private long cookingTimeInSeconds;
    private long restingTimeInSeconds;
    
}
