package HSEsslingen.WebServices.RecipesService.services.implementation;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import HSEsslingen.WebServices.RecipesService.assemblers.ImageAssembler;
import HSEsslingen.WebServices.RecipesService.assemblers.IngredientAssembler;
import HSEsslingen.WebServices.RecipesService.assemblers.RecipeAssembler;
import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.dtos.IngredientDTO;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;
import HSEsslingen.WebServices.RecipesService.repositories.RecipeRepository;
import HSEsslingen.WebServices.RecipesService.services.RecipeService;
import HSEsslingen.WebServices.RecipesService.services.helper.ServiceHelper;

@Service
public class RecipeServiceImpl implements RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final RecipeAssembler recipeAssembler;
    private final ImageAssembler imageAssembler;
    private final IngredientAssembler ingredientAssembler;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final ServiceHelper serviceHelper;


    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeAssembler recipeAssembler, ImageAssembler imageAssembler, 
    IngredientAssembler ingredientAssembler, PagedResourcesAssembler pagedResourcesAssembler, ServiceHelper serviceHelper) {
        this.recipeRepository = recipeRepository;
        this.recipeAssembler = recipeAssembler;
        this.imageAssembler = imageAssembler;
        this.ingredientAssembler = ingredientAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public CollectionModel<RecipeDTO> findAll(int offset, int limit, String sort, String fields, Specification<Recipe> recipeSpec) {
        PageRequest pageRequest;

        if(sort == null) {
            pageRequest = PageRequest.of(offset, limit);
        } else {
            List<Sort.Order> sortOrder = serviceHelper.getSortOrder(sort);
            pageRequest = PageRequest.of(offset, limit, Sort.by(sortOrder));
        }
        Page<Recipe> recipes = recipeRepository.findAll(recipeSpec, pageRequest);
        if(! CollectionUtils.isEmpty(recipes.getContent())) {
            return pagedResourcesAssembler.toModel(recipes, recipeAssembler);
        }
        return null;
    }

    @Override
    public RecipeDTO findByUUID(String uuid, String fields){
        Recipe recipe = recipeRepository.findByUuid(uuid).orElse(null);
        if (recipe != null) {
            try {
				return recipeAssembler.toModel(recipe, fields);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return null;
    }

    @Override
    public CollectionModel<ImageDTO> findRecipeImagesByUUID(String uuid) {
        Recipe recipe = recipeRepository.findByUuid(uuid).orElse(null);
        if(recipe != null && (! CollectionUtils.isEmpty(recipe.getImages())) ) {
            return imageAssembler.toCollectionModel(recipe.getImages());
        }
        return null;
    }

    @Override
    public CollectionModel<IngredientDTO> findRecipeIngredientsByUUID(String uuid) {
        Recipe recipe = recipeRepository.findByUuid(uuid).orElse(null);
        if(recipe != null && (! CollectionUtils.isEmpty(recipe.getIngredients())) ) {
            return ingredientAssembler.toCollectionModel(recipe.getIngredients());
        }
        return null;
    }

    @Transactional
    @Override
    public RecipeDTO insert(Recipe recipe) {
        String uuid = UUID.randomUUID().toString();
        recipe.setUuid(uuid);
        return recipeAssembler.toModel(recipeRepository.save(recipe));
    }

    @Transactional
    @Override
    public RecipeDTO replaceByUUID(String uuid, Recipe updatedRecipe) {
        Recipe oldRecipe = recipeRepository.findByUuid(uuid).orElse(null);
        if (oldRecipe != null && updatedRecipe != null) {
            recipeRepository.findById(oldRecipe.getId())
            .map(recipe -> {
                recipe.setTitle(updatedRecipe.getTitle());
                recipe.setSubTitle(updatedRecipe.getSubTitle());
                recipe.setDescription(updatedRecipe.getDescription());
                recipe.setCategory(updatedRecipe.getCategory());
                recipe.setServings(updatedRecipe.getServings());
                recipe.setCalories(updatedRecipe.getCalories());
                recipe.setLevelOfDifficulty(updatedRecipe.getLevelOfDifficulty());
                recipe.setWorkingTimeInSeconds(updatedRecipe.getWorkingTimeInSeconds());
                recipe.setCookingTimeInSeconds(updatedRecipe.getCookingTimeInSeconds());
                recipe.setRestingTimeInSeconds(updatedRecipe.getRestingTimeInSeconds());
                return recipeAssembler.toModel(recipeRepository.save(recipe));
            });
        }
        return null;
    }

    @Transactional
    @Override
    public RecipeDTO updateByUUID(String uuid, Recipe updatedRecipe) {
        Recipe oldRecipe = recipeRepository.findByUuid(uuid).orElse(null);
        if (oldRecipe != null && updatedRecipe != null) {
            recipeRepository.findById(oldRecipe.getId())
            .map(recipe -> {
                if(updatedRecipe.getTitle() != null) {
                    recipe.setTitle(updatedRecipe.getTitle());
                }
                if(updatedRecipe.getSubTitle() != null) {
                    recipe.setSubTitle(updatedRecipe.getSubTitle());
                }
                if(updatedRecipe.getDescription() != null) {
                    recipe.setDescription(updatedRecipe.getDescription());
                }
                if(updatedRecipe.getCategory() != null) {
                    recipe.setCategory(updatedRecipe.getCategory());
                }
                if(updatedRecipe.getServings() != null) {
                    recipe.setServings(updatedRecipe.getServings());
                }
                if(updatedRecipe.getCalories() != null) {
                    recipe.setCalories(updatedRecipe.getCalories());
                }
                if(updatedRecipe.getLevelOfDifficulty() != null) {
                    recipe.setLevelOfDifficulty(updatedRecipe.getLevelOfDifficulty());
                }
                if(updatedRecipe.getWorkingTimeInSeconds() != null) {
                    recipe.setWorkingTimeInSeconds(updatedRecipe.getWorkingTimeInSeconds());
                }
                if(updatedRecipe.getCookingTimeInSeconds() != null) {
                    recipe.setCookingTimeInSeconds(updatedRecipe.getCookingTimeInSeconds());
                }
                if(updatedRecipe.getRestingTimeInSeconds() != null) {
                    recipe.setRestingTimeInSeconds(updatedRecipe.getRestingTimeInSeconds());
                }
                return recipeAssembler.toModel(recipeRepository.save(recipe));
            });
        }
        return null;
    }

    @Transactional
    @Override
    public boolean removeByUUID(String uuid) {
        Recipe recipe = recipeRepository.findByUuid(uuid).orElse(null);
        boolean wasRecipeDeleted = false;
        if(recipe != null) {
            recipeRepository.deleteById(recipe.getId());
            wasRecipeDeleted = true;
        }
        return wasRecipeDeleted;
    }

}
