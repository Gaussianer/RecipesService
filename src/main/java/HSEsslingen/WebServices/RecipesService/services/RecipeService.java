package HSEsslingen.WebServices.RecipesService.services;

import java.util.HashMap;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;

import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;

public interface RecipeService {

    CollectionModel<RecipeDTO> findAll(int page, int size, String sort, String fields, Specification<Recipe> recipeSpec, HashMap<String, String> specificationKeyValuePairs);

    RecipeDTO findByUUID(String uuid);

    RecipeDTO insert(RecipeDTO recipe);

    RecipeDTO replaceByUUID(String uuid, RecipeDTO updatedRecipe);

    RecipeDTO updateByUUID(String uuid, RecipeDTO updatedRecipe);

    RecipeDTO removeByUUID(String uuid);
}
