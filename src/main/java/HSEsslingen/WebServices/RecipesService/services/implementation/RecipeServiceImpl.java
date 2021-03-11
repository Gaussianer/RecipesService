package HSEsslingen.WebServices.RecipesService.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import HSEsslingen.WebServices.RecipesService.assemblers.ImageAssembler;
import HSEsslingen.WebServices.RecipesService.assemblers.IngredientAssembler;
import HSEsslingen.WebServices.RecipesService.assemblers.RecipeAssembler;
import HSEsslingen.WebServices.RecipesService.controller.RecipeController;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Image;
import HSEsslingen.WebServices.RecipesService.entities.Ingredient;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;
import HSEsslingen.WebServices.RecipesService.exceptions.ImageNotFoundException;
import HSEsslingen.WebServices.RecipesService.exceptions.IngredientNotFoundException;
import HSEsslingen.WebServices.RecipesService.exceptions.MissingAttributeWhileCreatingRecipeException;
import HSEsslingen.WebServices.RecipesService.exceptions.RecipeNotFoundException;
import HSEsslingen.WebServices.RecipesService.exceptions.RecipeNotFoundWithFilterAttributs;
import HSEsslingen.WebServices.RecipesService.repositories.ImageRepository;
import HSEsslingen.WebServices.RecipesService.repositories.IngredientRepository;
import HSEsslingen.WebServices.RecipesService.repositories.RecipeRepository;
import HSEsslingen.WebServices.RecipesService.services.RecipeService;
import HSEsslingen.WebServices.RecipesService.services.helper.ServiceHelper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class RecipeServiceImpl implements RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final ImageRepository imageRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeAssembler recipeAssembler;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final ServiceHelper serviceHelper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ImageRepository imageRepository, IngredientRepository ingredientRepository,RecipeAssembler recipeAssembler,  
    PagedResourcesAssembler pagedResourcesAssembler, ServiceHelper serviceHelper) {
        this.recipeRepository = recipeRepository;
        this.recipeAssembler = recipeAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.imageRepository = imageRepository;
        this.ingredientRepository = ingredientRepository;
        this.serviceHelper = serviceHelper;
        this.pagedResourcesAssembler.setForceFirstAndLastRels(false);
    }

    @Override
    public CollectionModel<RecipeDTO> findAll(int offset, int limit, String sort, String fields, Specification<Recipe> recipeSpec, HashMap<String, String> specificationKeyValuePairs) {
        PageRequest pageRequest;

        if(sort == null) {
            pageRequest = PageRequest.of(offset, limit);
        } else {
            List<Sort.Order> sortOrder = serviceHelper.getSortOrder(sort);
            pageRequest = PageRequest.of(offset, limit, Sort.by(sortOrder));
        }
        Page<Recipe> recipes = recipeRepository.findAll(recipeSpec, pageRequest);
        if(CollectionUtils.isEmpty(recipes.getContent())) {
            throw new RecipeNotFoundWithFilterAttributs(Recipe.class, specificationKeyValuePairs);
        } else {
            return pagedResourcesAssembler.toModel(recipes, recipeAssembler, new Link("http://localhost:8080/recipes").withSelfRel());
        }
    }

    @Override
    public RecipeDTO findByUUID(String uuid){
        Recipe recipe = recipeRepository.findByUuid(uuid).orElseThrow(()-> 
        new RecipeNotFoundException(Recipe.class, uuid, "ID", uuid));
        return recipeAssembler.toModel(recipe);
    }

    @Override
    public RecipeDTO insert(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        String uuid = UUID.randomUUID().toString();
        recipe.setUuid(uuid);

        String[] missingAttributes = serviceHelper.getMissingRecipeAttributes(recipeDTO);
        if(missingAttributes != null) {
            throw new MissingAttributeWhileCreatingRecipeException(Recipe.class, missingAttributes);
        }

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
        if(recipeDTO.getImages() != null ){
            if(!recipeDTO.getImages().isEmpty()){
                for(String imageUUID : recipeDTO.getImages()){
                    Image image = imageRepository.findByUuid(imageUUID).orElseThrow(()-> 
                    new ImageNotFoundException(Image.class, imageUUID, "ID", imageUUID));
                    
                    if(image != null){
                        recipe.addImage(image);
                    } 
                }
            }
        }

        if(recipeDTO.getIngredients() != null ){
            if(!recipeDTO.getIngredients().isEmpty()){
                for(String ingredientUUID : recipeDTO.getIngredients()){
                    Ingredient ingredient = ingredientRepository.findByUuid(ingredientUUID).orElseThrow(()-> 
                    new IngredientNotFoundException(Image.class, ingredientUUID, "ID", ingredientUUID));
                    if(ingredient != null){
                        recipe.addIngredient(ingredient);
                    } 
                }
            }
        }
    
        return recipeAssembler.toModel(recipeRepository.save(recipe));
    }

    @Override
    public RecipeDTO replaceByUUID(String uuid, RecipeDTO updatedRecipe) {
        Recipe oldRecipe = recipeRepository.findByUuid(uuid).orElseThrow(()-> 
        new RecipeNotFoundException(Recipe.class, uuid, "ID", uuid));

        String[] missingAttributes = serviceHelper.getMissingRecipeAttributes(updatedRecipe);
        if(missingAttributes != null) {
            throw new MissingAttributeWhileCreatingRecipeException(Recipe.class, missingAttributes);
        }

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
                        Image image = imageRepository.findByUuid(imageUUID).orElseThrow(()-> 
                        new ImageNotFoundException(Image.class, imageUUID, "ID", imageUUID));
                        
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
                        Ingredient ingredient = ingredientRepository.findByUuid(ingredientUUID).orElseThrow(()-> 
                        new IngredientNotFoundException(Image.class, ingredientUUID, "ID", ingredientUUID));
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

    @Override
    public RecipeDTO updateByUUID(String uuid,  RecipeDTO updatedRecipe) {
        Recipe oldRecipe = recipeRepository.findByUuid(uuid).orElseThrow(()-> 
        new RecipeNotFoundException(Recipe.class, uuid, "ID", uuid));
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
                        Image image = imageRepository.findByUuid(imageUUID).orElseThrow(()-> 
                        new ImageNotFoundException(Image.class, imageUUID, "ID", imageUUID));
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
                        Ingredient ingredient = ingredientRepository.findByUuid(ingredientUUID).orElseThrow(()-> 
                        new IngredientNotFoundException(Image.class, ingredientUUID, "ID", ingredientUUID));
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
    public RecipeDTO removeByUUID(String uuid) {

        Recipe thisRecipe = recipeRepository.findByUuid(uuid).orElseThrow(()-> 
        new RecipeNotFoundException(Recipe.class, uuid, "ID", uuid));

        if(thisRecipe != null) {
            if(thisRecipe.getImages() != null){

                for(Image image : thisRecipe.getImages()){
                    thisRecipe.resetImage(image);
                }
                thisRecipe.resetImageList();
            }
        if(thisRecipe.getIngredients() != null) {

            for(Ingredient ingredient : thisRecipe.getIngredients()){
                thisRecipe.resetIngredient(ingredient);
            }
            thisRecipe.resetIngredientList();
        }

        recipeRepository.deleteById(thisRecipe.getId());
        }
        return recipeAssembler.toModel(thisRecipe);
    }

}
