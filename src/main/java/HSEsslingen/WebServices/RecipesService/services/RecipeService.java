package HSEsslingen.WebServices.RecipesService.services;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;

import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;

public interface RecipeService {

    // CollectionModel<RecipeDTO> findAll(int page, int size, String[] sort, String dir);

    CollectionModel<RecipeDTO> findAll(int page, int size, String sort, Specification<Recipe> recipeSpec);
    
    CollectionModel<ImageDTO> findRecipeImagesByUUID(String uuid);

    CollectionModel<IngredientDTO> findRecipeIngredientsByUUID(String uuid);

    RecipeDTO findByUUID(String uuid, String[] fields);

    RecipeDTO insert(Recipe recipe);

    RecipeDTO replaceByUUID(String uuid, Recipe recipe);

    RecipeDTO updateByUUID(String uuid, Recipe recipe);

    boolean removeByUUID(String uuid);
}
