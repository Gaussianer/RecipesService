package HSEsslingen.WebServices.RecipesService.services;

import org.springframework.hateoas.CollectionModel;

import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;

public interface ImageService {

    ImageDTO findByUUID(String uuid);

    RecipeDTO findImageRecipeByUUID(String uuid);
}
