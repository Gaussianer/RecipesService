// package HSEsslingen.WebServices.RecipesService.assemblers;

// import org.springframework.hateoas.server.RepresentationModelAssembler;
// import org.springframework.stereotype.Component;

// import HSEsslingen.WebServices.RecipesService.controller.RecipeController;
// import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
// import HSEsslingen.WebServices.RecipesService.entities.Image;


// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// @Component
// public class ImageAssembler implements RepresentationModelAssembler<Image, ImageDTO> {

//     @Override
//     public ImageDTO toModel(Image imageEntity) {
//         ImageDTO imageDTO = new ImageDTO(
//             imageEntity.getId(), 
//             imageEntity.getName(),
//             imageEntity.getUrl()
//             );

//             imageDTO.add(linkTo(methodOn(RecipeController.class).findByUuid(imageEntity.getId())).withSelfRel());
//         return imageDTO;
//     }
// }