package HSEsslingen.WebServices.RecipesService.dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Relation(collectionRelation = "ingredients")
@Data
@AllArgsConstructor
public class IngredientDTO  extends RepresentationModel<IngredientDTO> {
 
	private String id;
    private String name;
    private Double quantity;
    private String unit;

}
