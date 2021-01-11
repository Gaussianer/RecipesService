package HSEsslingen.WebServices.RecipesService.services.implementation;

import java.util.Iterator;
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
import HSEsslingen.WebServices.RecipesService.entities.Image;
import HSEsslingen.WebServices.RecipesService.entities.Ingredient;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;
import HSEsslingen.WebServices.RecipesService.repositories.ImageRepository;
import HSEsslingen.WebServices.RecipesService.repositories.IngredientRepository;
import HSEsslingen.WebServices.RecipesService.repositories.RecipeRepository;
import HSEsslingen.WebServices.RecipesService.services.ImageService;
import HSEsslingen.WebServices.RecipesService.services.RecipeService;
import HSEsslingen.WebServices.RecipesService.services.helper.ServiceHelper;

@Service
public class RecipeServiceImpl implements RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final ImageRepository imageRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeAssembler recipeAssembler;
    private final ImageAssembler imageAssembler;
    private final IngredientAssembler ingredientAssembler;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final ServiceHelper serviceHelper;


    public RecipeServiceImpl(RecipeRepository recipeRepository, ImageRepository imageRepository, IngredientRepository ingredientRepository,RecipeAssembler recipeAssembler, 
    ImageAssembler imageAssembler, IngredientAssembler ingredientAssembler, PagedResourcesAssembler pagedResourcesAssembler, ServiceHelper serviceHelper) {
        this.recipeRepository = recipeRepository;
        this.recipeAssembler = recipeAssembler;
        this.imageAssembler = imageAssembler;
        this.ingredientAssembler = ingredientAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.imageRepository = imageRepository;
        this.ingredientRepository = ingredientRepository;
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
    public RecipeDTO findByUUID(String uuid){
        Recipe recipe = recipeRepository.findByUuid(uuid).orElse(null);
        if (recipe != null) {
			return recipeAssembler.toModel(recipe);
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

    @Override
    public RecipeDTO insert(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        String uuid = UUID.randomUUID().toString();
        recipe.setUuid(uuid);

        if(recipeDTO.getTitle() != null) {
            recipe.setTitle(recipeDTO.getTitle());
        }
        if(recipeDTO.getSubTitle() != null) {
            recipe.setSubTitle(recipeDTO.getSubTitle());
        }
        if(recipeDTO.getDescription() != null) {
            recipe.setDescription(recipeDTO.getDescription());
        }
        if(recipeDTO.getCategory() != null) {
            recipe.setCategory(recipeDTO.getCategory());
        }
        if(recipeDTO.getServings() != null) {
            recipe.setServings(recipeDTO.getServings());
        }
        if(recipeDTO.getCalories() != null) {
            recipe.setCalories(recipeDTO.getCalories());
        }
        if(recipeDTO.getLevelOfDifficulty() != null) {
            recipe.setLevelOfDifficulty(recipeDTO.getLevelOfDifficulty());
        }
        if(recipeDTO.getWorkingTimeInSeconds() != null) {
            recipe.setWorkingTimeInSeconds(recipeDTO.getWorkingTimeInSeconds());
        }
        if(recipeDTO.getCookingTimeInSeconds() != null) {
            recipe.setCookingTimeInSeconds(recipeDTO.getCookingTimeInSeconds());
        }
        if(recipeDTO.getRestingTimeInSeconds() != null) {
            recipe.setRestingTimeInSeconds(recipeDTO.getRestingTimeInSeconds());
        }
        for(String imageUUID : recipeDTO.getImages()){
            Image image = imageRepository.findByUuid(imageUUID).orElse(null);
            
            if(image != null){
                recipe.addImage(image);
            } 
        }

        for(String ingredientUUID : recipeDTO.getIngredients()){
            Ingredient ingredient = ingredientRepository.findByUuid(ingredientUUID).orElse(null);

            if(ingredient != null){
                recipe.addIngredient(ingredient);
            } 
        }
    
        return recipeAssembler.toModel(recipeRepository.save(recipe));
    }

    @Override
    public RecipeDTO replaceByUUID(String uuid, RecipeDTO updatedRecipe) {
        Recipe oldRecipe = recipeRepository.findByUuid(uuid).orElse(null);
        
        if (oldRecipe != null && updatedRecipe != null) {
            recipeRepository.findById(oldRecipe.getId())
            .map(recipe -> {
                Recipe tempRecipe = recipe;
                boolean isSubResourceAvailable = true;
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

                for(Image image : recipe.getImages()){
                    recipe.removeImage(image);
                }
                for(String imageUUID : updatedRecipe.getImages()){
                    Image image = imageRepository.findByUuid(imageUUID).orElse(null);
                    if(image != null) {
                        recipe.addImage(image);
                    } else {
                        isSubResourceAvailable = false;
                    }
                    recipe.addImage(image);
                }

                for(Ingredient ingredient : recipe.getIngredients()){
                    recipe.removeIngredient(ingredient);
                }
                for(String ingredientUUID : updatedRecipe.getIngredients()){
                    Ingredient ingredient = ingredientRepository.findByUuid(ingredientUUID).orElse(null);
                    if(ingredient != null) {
                        recipe.addIngredient(ingredient);
                    } else {
                        isSubResourceAvailable = false;
                    }
                }
                if(isSubResourceAvailable) {
                    return recipeAssembler.toModel(recipeRepository.save(recipe));
                }
                recipe = tempRecipe;
                return null;
            });
        }
        return null;
    }

    @Override
    public RecipeDTO updateByUUID(String uuid,  RecipeDTO updatedRecipe) {
        Recipe oldRecipe = recipeRepository.findByUuid(uuid).orElse(null);
        if (oldRecipe != null && updatedRecipe != null) {
            recipeRepository.findById(oldRecipe.getId())
            .map(recipe -> {
                Recipe tempRecipe = recipe;
                boolean isSubResourceAvailable = true;
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
                if(updatedRecipe.getImages() != null){

                    for(Image image : recipe.getImages()){
                        recipe.resetImage(image);
                    }
                    recipe.resetImageList();

                    for(String imageUUID : updatedRecipe.getImages()) {
                        // Wenn Image nicht gefunden wird -> Fehlermeldung an Client!
                        Image image = imageRepository.findByUuid(imageUUID).orElse(null);
                        if(image != null) {
                            recipe.addImage(image);
                        } else {
                            isSubResourceAvailable = false;
                        }
                    }
                }
                if(updatedRecipe.getIngredients() != null) {

                    for(Ingredient ingredient : recipe.getIngredients()){
                        recipe.resetIngredient(ingredient);
                    }
                    recipe.resetIngredientList();

                    for(String ingredientUUID : updatedRecipe.getIngredients()){
                        // Wenn Ingredient nicht gefunden wird -> Fehlermeldung an Client!
                        Ingredient ingredient = ingredientRepository.findByUuid(ingredientUUID).orElse(null);
                        if(ingredient != null) {
                            recipe.addIngredient(ingredient);
                        } else {
                            isSubResourceAvailable = false;
                        }
                    }
                }
                if(isSubResourceAvailable) {
                    return recipeAssembler.toModel(recipeRepository.save(recipe));
                }
                recipe = tempRecipe;
                return null;
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
