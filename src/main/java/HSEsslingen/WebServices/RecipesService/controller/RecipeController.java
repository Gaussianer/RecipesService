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
    public ResponseEntity findById(@PathVariable String id) {

        RecipeDTO recipeDTO = recipeService.findById(id);
        if (recipeDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeDTO);

    }

    
    @PostMapping
    public ResponseEntity insertRecipe(@RequestBody Recipe recipe) {
        
        RecipeDTO recipeDTO = recipeService.insert(recipe);
        return ResponseEntity.created(recipeDTO.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(recipeDTO);

    }
    // ###############################################################//#region

    // private final RecipeRepository recipeRepository;
    // private final RecipeModelAssembler recipeModelAssembler;
    
    // RecipeController(RecipeRepository recipeRepository, RecipeModelAssembler recipeModelAssembler) {
    //     this.recipeRepository = recipeRepository;
    //     this.recipeModelAssembler = recipeModelAssembler;
    // }
    
    // @GetMapping("/api/v1/recipes")
    // public CollectionModel<EntityModel<Recipe>> getAllRecipes() {
    //     List<EntityModel<Recipe>> recipes = recipeRepository.findAll().stream()
    //     .map(recipeModelAssembler::toModel)
    //     .collect(Collectors.toList());

    //     return CollectionModel.of(recipes,
    //     linkTo(methodOn(RecipeController.class).getAllRecipes()).withSelfRel());
    // }

    // @GetMapping("api/v1/recipes/{id}")
    // public EntityModel<Recipe> getRecipeById(@PathVariable String id) {
    //     Recipe recipe = recipeRepository.findById(id)
    //     .orElseThrow(() -> new RecipeNotFoundException(id));

    //     return recipeModelAssembler.toModel(recipe);
    // }


    // @PostMapping("/api/v1/recipes")
    // ResponseEntity<?> newEmployee(@RequestBody Recipe newRecipe) {
    
    //   EntityModel<Recipe> entityModel = recipeModelAssembler.toModel(recipeRepository.save(newRecipe));
    
    //   return ResponseEntity 
    //       .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
    //       .body(entityModel);
    // }

    // @PutMapping("/api/v1/recipes/{id}")
    // ResponseEntity<?> replaceRecipeWithId(@RequestBody Recipe newRecipe, @PathVariable String id) {
    
    //     Recipe updatedRecipe = recipeRepository.findById(id) 
    //       .map(recipe -> {
    //         recipe.setTitle(newRecipe.getTitle());
    //         recipe.setSubTitle(newRecipe.getSubTitle());
    //         recipe.setDescription(newRecipe.getDescription());
    //         recipe.setImageURL(newRecipe.getImageURL());
    //         recipe.setCategory(newRecipe.getCategory());
    //         recipe.setServings(newRecipe.getServings());
    //         recipe.setCalories(newRecipe.getCalories());
    //         recipe.setLevelOfDifficulty(newRecipe.getLevelOfDifficulty());
    //         recipe.setWorkingTimeInSeconds(newRecipe.getWorkingTimeInSeconds());
    //         recipe.setCookingTimeInSeconds(newRecipe.getCookingTimeInSeconds());
    //         recipe.setRestingTimeInSeconds(newRecipe.getRestingTimeInSeconds());
    //         return recipeRepository.save(recipe);
    //       }) 
    //       .orElseGet(() -> { // Hier wird ein Rezept mit einer anderen ID Angelegt?!
    //         newRecipe.setId(id); // Eventuell lieber Fehlermeldung?!
    //         return recipeRepository.save(newRecipe);
    //       });
    
    //   EntityModel<Recipe> entityModel = recipeModelAssembler.toModel(updatedRecipe);
    
    //   return ResponseEntity
    //       .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) // 201 = created -> Fraglich ob es der richtige HTTP-Statuscode ist (laut Spring Boot)
    //       .body(entityModel);
    // }

    // @DeleteMapping("/employees/{id}")
    // public ResponseEntity<?> deleteRecipeById(@PathVariable String id) {
    //     recipeRepository.deleteById(id);
    //     return ResponseEntity.noContent().build();
    // }
}