package HSEsslingen.WebServices.RecipesService.assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import HSEsslingen.WebServices.RecipesService.controller.ImageController;
import HSEsslingen.WebServices.RecipesService.controller.RecipeController;
import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.entities.Image;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;

@Component
public class ImageAssembler implements RepresentationModelAssembler<Image, ImageDTO> {

    @Override
    public ImageDTO toModel(Image imageEntity) {
        ImageDTO imageDTO = new ImageDTO(
            imageEntity.getUuid(), 
            imageEntity.getName(),
            imageEntity.getUrl()
            );
            if(imageEntity.getIngredient() != null) {
            // Wenn Ingredient ausgebaut wird, dann hier noch wie bei Recipe ausbessern
            // imageDTO.add(WebMvcLinkBuilder.linkTo(methodOn(ImageController.class).findImageRecipeByUUID(imageEntity.getUuid())).withRel("recipe")); 
            } else if (imageEntity.getRecipe() != null) { 
            imageDTO.add(new Link("http://localhost:8080/recipes/" + imageEntity.getRecipe().getUuid()));
            }
            imageDTO.add(linkTo(methodOn(ImageController.class).getImageByUUID(imageEntity.getUuid())).withSelfRel());
        return imageDTO;
    }
}
