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

@Component
public class ImageAssembler implements RepresentationModelAssembler<Image, ImageDTO> {

    @Override
    public ImageDTO toModel(Image imageEntity) {
        ImageDTO imageDTO = new ImageDTO(
            imageEntity.getId(), 
            imageEntity.getName(),
            imageEntity.getUrl()
            );
            
            imageDTO.add(WebMvcLinkBuilder.linkTo(methodOn(ImageController.class).findImageRecipe(imageEntity.getId())).withRel("recipe"));
            imageDTO.add(linkTo(methodOn(ImageController.class).findById(imageEntity.getId())).withSelfRel());
        return imageDTO;
    }
}