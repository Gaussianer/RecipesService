package HSEsslingen.WebServices.RecipesService.services;

import org.springframework.hateoas.CollectionModel;

import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;

public interface RecipeService {

    CollectionModel<RecipeDTO> findAll(int page, int size, String[] sort, String dir);

    RecipeDTO findById(String id);

    RecipeDTO findByTitle(String title);

    RecipeDTO insert(Recipe recipe);
}
