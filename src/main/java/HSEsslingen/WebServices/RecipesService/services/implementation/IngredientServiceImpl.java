package HSEsslingen.WebServices.RecipesService.services.implementation;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import HSEsslingen.WebServices.RecipesService.assemblers.IngredientAssembler;
import HSEsslingen.WebServices.RecipesService.assemblers.RecipeAssembler;
import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;
import HSEsslingen.WebServices.RecipesService.entities.Ingredient;
import HSEsslingen.WebServices.RecipesService.repositories.IngredientRepository;
import HSEsslingen.WebServices.RecipesService.services.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;
    private final IngredientAssembler ingredientAssembler;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientAssembler ingredientAssembler) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientAssembler = ingredientAssembler;
    }

    @Override
    public IngredientDTO findByUUID(String id) {
        Ingredient ingredient = ingredientRepository.findByUuid(id).orElse(null);
        if(ingredient != null) {
            return ingredientAssembler.toModel(ingredient);
        }
        return null;
    }

}
