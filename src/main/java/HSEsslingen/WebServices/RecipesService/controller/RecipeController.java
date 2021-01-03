package HSEsslingen.WebServices.RecipesService.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;
import HSEsslingen.WebServices.RecipesService.services.RecipeService;



@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity findAll(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "100") Integer size,
        @RequestParam(required = false) String[] sort,
        @RequestParam(required = false, defaultValue = "asc") String dir) {

        CollectionModel<RecipeDTO> recipes = recipeService.findAll(page, size, sort, dir);

        if (recipes != null) {
            return ResponseEntity.ok(recipes);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findByUUID(@PathVariable String id) {

        RecipeDTO recipeDTO = recipeService.findByUUID(id);
        if (recipeDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeDTO);

    }

    @GetMapping("/{id}/images")
    public ResponseEntity findRecipeImagesByUUID(@PathVariable String id) {
        CollectionModel<ImageDTO> images = recipeService.findRecipeImagesByUUID(id);
        if(images != null) return ResponseEntity.ok(images);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/ingredients")
    public ResponseEntity findRecipeIngredientsByUUID(@PathVariable String id) {
        CollectionModel<IngredientDTO> ingredients = recipeService.findRecipeIngredientsByUUID(id);
        if(ingredients != null) return ResponseEntity.ok(ingredients);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity insertRecipe(@RequestBody Recipe recipe) {
        
        RecipeDTO recipeDTO = recipeService.insert(recipe);
        return ResponseEntity.created(recipeDTO.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(recipeDTO);

    }
}