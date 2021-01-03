package HSEsslingen.WebServices.RecipesService.services.implementation;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import HSEsslingen.WebServices.RecipesService.entities.Recipe;
import HSEsslingen.WebServices.RecipesService.repositories.ImageRepository;
import HSEsslingen.WebServices.RecipesService.repositories.IngredientRepository;
import HSEsslingen.WebServices.RecipesService.repositories.RecipeRepository;
import HSEsslingen.WebServices.RecipesService.services.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final ImageRepository imageRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeAssembler recipeAssembler;
    private final ImageAssembler imageAssembler;
    private final IngredientAssembler ingredientAssembler;
    private final PagedResourcesAssembler pagedResourcesAssembler;


    public RecipeServiceImpl(RecipeRepository recipeRepository, ImageRepository imageRepository, IngredientRepository ingredientRepository, 
    RecipeAssembler recipeAssembler, ImageAssembler imageAssembler, IngredientAssembler ingredientAssembler, PagedResourcesAssembler pagedResourcesAssembler) {
        this.recipeRepository = recipeRepository;
        this.imageRepository = imageRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeAssembler = recipeAssembler;
        this.imageAssembler = imageAssembler;
        this.ingredientAssembler = ingredientAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }


    @Override
    public CollectionModel<RecipeDTO> findAll(int page, int size, String[] sort, String dir) {
        PageRequest pageRequest;
        Sort.Direction direction;

        if(sort == null) {
            pageRequest = PageRequest.of(page, size);
        }
        else {
            if(dir.equalsIgnoreCase("asc")) {
                direction = Sort.Direction.ASC; 
            }
            else {
                direction = Sort.Direction.DESC;
            }
            pageRequest = PageRequest.of(page, size, Sort.by(direction, sort));
            

        }
        Page<Recipe> recipes = recipeRepository.findAll(pageRequest);

        if(! CollectionUtils.isEmpty(recipes.getContent())) {
            return pagedResourcesAssembler.toModel(recipes, recipeAssembler);
        }
        return null;
    }

    @Override
    public RecipeDTO findByUUID(String uuid) {
        Recipe recipe = recipeRepository.findByUuid(uuid).orElse(null);
        if(recipe != null) {
            return recipeAssembler.toModel(recipe);
        }
        return null;
    }

    @Override
    public RecipeDTO findByTitle(String title) {
        Recipe recipe = recipeRepository.findByTitle(title).orElse(null);
        if(recipe != null) {
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


                // LOGIK Auslagern in PUT /recipes/ID/images ?!
                // recipe.getImages().forEach(image -> {
                //     imageRepository.delete(image);
                // });

                // updatedRecipe.getImages().forEach(image -> {
                //     String newUuid = UUID.randomUUID().toString();
                //     image.setUuid(newUuid);
                //     recipe.addImage(image);
                // });

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

            recipe.getImages().forEach(image -> {
                imageRepository.deleteById(image.getId());
            });

            recipe.getIngredients().forEach(ingredient -> {
                ingredientRepository.deleteById(ingredient.getId());
            });

            recipeRepository.deleteById(recipe.getId());
            wasRecipeDeleted = true;
        }
        return wasRecipeDeleted;
    }

}
