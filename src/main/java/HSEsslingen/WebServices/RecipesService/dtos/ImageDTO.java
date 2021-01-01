package HSEsslingen.WebServices.RecipesService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "images")
@Data
@AllArgsConstructor
public class ImageDTO extends RepresentationModel<RecipeDTO> {

    private String id;
    private String name;
    private String url;
    
}