package HSEsslingen.WebServices.RecipesService.services.implementation;

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
import HSEsslingen.WebServices.RecipesService.entities.Recipe;
import HSEsslingen.WebServices.RecipesService.repositories.RecipeRepository;
import HSEsslingen.WebServices.RecipesService.services.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final RecipeAssembler recipeAssembler;
    private final ImageAssembler imageAssembler;
    private final IngredientAssembler ingredientAssembler;
    private final PagedResourcesAssembler pagedResourcesAssembler;


    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeAssembler recipeAssembler, ImageAssembler imageAssembler, IngredientAssembler ingredientAssembler, PagedResourcesAssembler pagedResourcesAssembler) {
        this.recipeRepository = recipeRepository;
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
    public RecipeDTO findById(String id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
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
    public CollectionModel<ImageDTO> findRecipeImages(String id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if(recipe != null && (! CollectionUtils.isEmpty(recipe.getImages())) ) {
            return imageAssembler.toCollectionModel(recipe.getImages());
        }
        return null;
    }

    @Override
    public CollectionModel<IngredientDTO> findRecipeIngredients(String id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if(recipe != null && (! CollectionUtils.isEmpty(recipe.getImages())) ) {
            return ingredientAssembler.toCollectionModel(recipe.getIngredients());
        }
        return null;
    }

    @Transactional
    @Override
    public RecipeDTO insert(Recipe recipe) {
        return recipeAssembler.toModel(recipeRepository.save(recipe));
    }

}
