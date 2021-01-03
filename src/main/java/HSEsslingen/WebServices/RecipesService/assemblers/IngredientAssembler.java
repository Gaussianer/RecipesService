package HSEsslingen.WebServices.RecipesService.assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import HSEsslingen.WebServices.RecipesService.controller.IngredientController;
import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;
import HSEsslingen.WebServices.RecipesService.entities.Ingredient;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class IngredientAssembler implements RepresentationModelAssembler<Ingredient, IngredientDTO> {

    @Override
    public IngredientDTO toModel(Ingredient ingredientEntity) {
            
            IngredientDTO ingredientDTO = new IngredientDTO(
                ingredientEntity.getUuid(),
                ingredientEntity.getName(),
                ingredientEntity.getQuantity(),
                ingredientEntity.getUnit()
                );
                

            ingredientDTO.add(linkTo(methodOn(IngredientController.class).findByUUID(ingredientEntity.getUuid())).withSelfRel());
        return ingredientDTO;
    }
}
