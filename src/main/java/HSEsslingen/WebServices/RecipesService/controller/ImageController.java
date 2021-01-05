package HSEsslingen.WebServices.RecipesService.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Image;
import HSEsslingen.WebServices.RecipesService.services.ImageService;


@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    
    @GetMapping
    (produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity getAllImages(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "100") Integer size, // Welche DEFAULT PARAMS?!
        @RequestParam(required = false) String[] sort,
        @RequestParam(required = false, defaultValue = "asc") String dir) {

        CollectionModel<ImageDTO> images = imageService.findAll(page, size, sort, dir);

        if (images != null) {
            return ResponseEntity.ok(images);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{imageId}")
    public ResponseEntity getImageByUUID(@PathVariable String imageId) {
        ImageDTO image = imageService.findByUUID(imageId);
        if(image != null) return ResponseEntity.ok(image);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity addImage(@RequestBody Image image) {
        try {
            if (image != null) {
                ImageDTO imageDTO = imageService.insert(image);
                return ResponseEntity.created(imageDTO.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(imageDTO);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    (value = "/{imageId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity removeRecipeByUUID(@PathVariable String imageId) {
        try {
            if (imageId != null && imageId != "") {
                boolean recipeWasDeleted = imageService.removeByUUID(imageId);
                if (!recipeWasDeleted){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    (value = "/{imageId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity replaceRecipeByUUID(@PathVariable String imageId, @RequestBody Image image) {
        try {
            if (imageId != null && image != null) {
                ImageDTO imageDTO = imageService.replaceByUUID(imageId, image);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}