package HSEsslingen.WebServices.RecipesService.services;

import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;

public interface IngredientService {
    
    IngredientDTO findById(String id);

}
