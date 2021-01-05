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

import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;
import HSEsslingen.WebServices.RecipesService.entities.Ingredient;
import HSEsslingen.WebServices.RecipesService.services.IngredientService;



@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    (produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity getAllImages(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "100") Integer size, // Welche DEFAULT PARAMS?!
        @RequestParam(required = false) String[] sort,
        @RequestParam(required = false, defaultValue = "asc") String dir) {

        CollectionModel<IngredientDTO> images = ingredientService.findAll(page, size, sort, dir);

        if (images != null) {
            return ResponseEntity.ok(images);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity findByUUID(@PathVariable String ingredientId) {
        IngredientDTO ingredient = ingredientService.findByUUID(ingredientId);
        if(ingredient != null) return ResponseEntity.ok(ingredient);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity addIngredient(@RequestBody Ingredient ingredient) {
        try {
            if (ingredient != null) {
                IngredientDTO ingredientDTO = ingredientService.insert(ingredient);
                return ResponseEntity.created(ingredientDTO.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(ingredientDTO);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    (value = "/{ingredientId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity removeRecipeByUUID(@PathVariable String ingredientId) {
        try {
            if (ingredientId != null && ingredientId != "") {
                boolean ingredientWasDeleted = ingredientService.removeByUUID(ingredientId);
                if (!ingredientWasDeleted){
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
    (value = "/{ingredientId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity replaceRecipeByUUID(@PathVariable String ingredientId, @RequestBody Ingredient ingredient) {
        try {
            if (ingredientId != null && ingredient != null) {
                IngredientDTO ingredientDTO = ingredientService.replaceByUUID(ingredientId, ingredient);
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