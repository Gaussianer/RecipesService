package HSEsslingen.WebServices.RecipesService.services;

import org.springframework.hateoas.CollectionModel;

import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;
import HSEsslingen.WebServices.RecipesService.entities.Ingredient;

public interface IngredientService {
    
    CollectionModel<IngredientDTO> findAll(int page, int size, String[] sort, String dir);

    IngredientDTO insert(Ingredient image);

    IngredientDTO findByUUID(String uuid);

    boolean removeByUUID(String uuid);

    IngredientDTO replaceByUUID(String uuid, Ingredient ingredient);
}
