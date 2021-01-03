package HSEsslingen.WebServices.RecipesService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.services.ImageService;



@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity findByUUID(@PathVariable String id) {
        ImageDTO image = imageService.findByUUID(id);
        if(image != null) return ResponseEntity.ok(image);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/recipe")
    public ResponseEntity findImageRecipeByUUID(@PathVariable String id) {
        RecipeDTO recipe = imageService.findImageRecipeByUUID(id);
        if(recipe != null) return ResponseEntity.ok(recipe);
        return ResponseEntity.notFound().build();
    }
}