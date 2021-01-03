package HSEsslingen.WebServices.RecipesService.assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import HSEsslingen.WebServices.RecipesService.controller.ImageController;
import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.entities.Image;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ImageAssembler implements RepresentationModelAssembler<Image, ImageDTO> {

    @Override
    public ImageDTO toModel(Image imageEntity) {
        ImageDTO imageDTO = new ImageDTO(
            imageEntity.getUuid(), 
            imageEntity.getName(),
            imageEntity.getUrl()
            );
            
            imageDTO.add(WebMvcLinkBuilder.linkTo(methodOn(ImageController.class).findImageRecipeByUUID(imageEntity.getUuid())).withRel("recipe"));
            imageDTO.add(linkTo(methodOn(ImageController.class).findByUUID(imageEntity.getUuid())).withSelfRel());
        return imageDTO;
    }
}