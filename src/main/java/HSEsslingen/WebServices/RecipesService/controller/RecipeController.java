package HSEsslingen.WebServices.RecipesService.controller;

import java.util.UUID;

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
import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;
import HSEsslingen.WebServices.RecipesService.services.ImageService;
import HSEsslingen.WebServices.RecipesService.services.RecipeService;



@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    private RecipeService recipeService;
    private ImageService imageService;

    public RecipeController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    // ####################################################################################################################
    // ##############################                   RECIPES                              ##############################
    // ####################################################################################################################

    @GetMapping
    (produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity getAllRecipes(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "100") Integer size, // Welche DEFAULT PARAMS?!
        @RequestParam(required = false) String[] sort,
        @RequestParam(required = false, defaultValue = "asc") String dir) {

        CollectionModel<RecipeDTO> recipes = recipeService.findAll(page, size, sort, dir);

        if (recipes != null) {
            return ResponseEntity.ok(recipes);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    (value = "/{recipeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity getRecipeByUUID(@PathVariable String recipeId) {

        RecipeDTO recipeDTO = recipeService.findByUUID(recipeId);
        if (recipeDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeDTO);

    }

    @PostMapping
    public ResponseEntity addRecipe(@RequestBody Recipe recipe) {
        try {
            if (recipe != null) {
                RecipeDTO recipeDTO = recipeService.insert(recipe);
                return ResponseEntity.created(recipeDTO.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(recipeDTO);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    (value = "/{recipeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity removeRecipeByUUID(@PathVariable String recipeId) {
        try {
            if (recipeId != null && recipeId != "") {
                boolean recipeWasDeleted = recipeService.removeByUUID(recipeId);
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
    (value = "/{recipeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity replaceRecipeByUUID(@PathVariable String recipeId, @RequestBody Recipe recipe) {
        try {
            if (recipeId != null && recipe != null) {
                RecipeDTO recipeDTO = recipeService.replaceByUUID(recipeId, recipe);
                return ResponseEntity.ok(recipeDTO);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // ####################################################################################################################
    // ##############################                   RECIPES/IMAGES                       ##############################
    // ####################################################################################################################

    @GetMapping("/{recipeId}/images")
    public ResponseEntity getRecipeImagesByRecipeUUID(@PathVariable String recipeId) {
        CollectionModel<ImageDTO> images = recipeService.findRecipeImagesByUUID(recipeId);
        if(images != null) return ResponseEntity.ok(images);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    (value = "/{recipeId}/images/{imageId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity findImageRecipeByUUIDs(@PathVariable String recipeId, @PathVariable String imageId) {
        ImageDTO image = imageService.findByUUID(imageId);
        if(image != null) return ResponseEntity.ok(image);
        return ResponseEntity.notFound().build();
    }

    // ####################################################################################################################
    // ##############################                   RECIPES/INGREDIENTS                  ##############################
    // ####################################################################################################################

    @GetMapping("/{id}/ingredients")
    public ResponseEntity getRecipeIngredientsByRecipeUUID(@PathVariable String id) {
        CollectionModel<IngredientDTO> ingredients = recipeService.findRecipeIngredientsByUUID(id);
        if(ingredients != null) return ResponseEntity.ok(ingredients);

        return ResponseEntity.noContent().build();
    }

}