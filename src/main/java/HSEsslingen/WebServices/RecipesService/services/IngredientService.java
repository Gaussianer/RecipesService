package HSEsslingen.WebServices.RecipesService.services;

import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;

public interface IngredientService {
    
    IngredientDTO findByUUID(String uuid);

}
