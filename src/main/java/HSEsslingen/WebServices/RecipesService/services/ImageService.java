package HSEsslingen.WebServices.RecipesService.services;

import org.springframework.hateoas.CollectionModel;

import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Image;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;

public interface ImageService {

    CollectionModel<ImageDTO> findAll(int page, int size, String[] sort, String dir);

    ImageDTO insert(Image image);

    ImageDTO findByUUID(String uuid);

    RecipeDTO findImageRecipeByUUID(String uuid);
    
    ImageDTO replaceByUUID(String uuid, Image image);

    boolean removeByUUID(String uuid);
}
