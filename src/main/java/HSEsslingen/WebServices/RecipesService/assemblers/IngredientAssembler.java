package HSEsslingen.WebServices.RecipesService.assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import HSEsslingen.WebServices.RecipesService.controller.ImageController;
import HSEsslingen.WebServices.RecipesService.controller.IngredientController;
import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;
import HSEsslingen.WebServices.RecipesService.entities.Image;
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
                
            for(Image image : ingredientEntity.getImages()){
                ingredientDTO.add(linkTo(methodOn(ImageController.class).getImageByUUID(image.getUuid())).withRel("images"));
            }

            ingredientDTO.add(linkTo(methodOn(IngredientController.class).getIngredientByUUID(ingredientEntity.getUuid())).withSelfRel());
        return ingredientDTO;
    }
}
