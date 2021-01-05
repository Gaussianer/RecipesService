package HSEsslingen.WebServices.RecipesService.services.implementation;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    private final PagedResourcesAssembler pagedResourcesAssembler;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientAssembler ingredientAssembler, PagedResourcesAssembler pagedResourcesAssembler) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientAssembler = ingredientAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Override
    public CollectionModel<IngredientDTO> findAll(int page, int size, String[] sort, String dir) {
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
        Page<Ingredient> ingredients = ingredientRepository.findAll(pageRequest);

        if(! CollectionUtils.isEmpty(ingredients.getContent())) {
            return pagedResourcesAssembler.toModel(ingredients, ingredientAssembler);
        }
        return null;
    }
    
    @Override
    public IngredientDTO findByUUID(String id) {
        Ingredient ingredient = ingredientRepository.findByUuid(id).orElse(null);
        if(ingredient != null) {
            return ingredientAssembler.toModel(ingredient);
        }
        return null;
    }

    @Transactional
    @Override
    public IngredientDTO insert(Ingredient ingredient) {
        String uuid = UUID.randomUUID().toString();
        ingredient.setUuid(uuid);
        return ingredientAssembler.toModel(ingredientRepository.save(ingredient));
    }

    @Transactional
    @Override
    public boolean removeByUUID(String uuid) {
        Ingredient ingredient = ingredientRepository.findByUuid(uuid).orElse(null);
        boolean wasIngredientDeleted = false;
        if(ingredient != null) {

            ingredient.getImages().forEach(image -> {
                image.setIngredient(null);
            });

            ingredientRepository.deleteById(ingredient.getId());
            wasIngredientDeleted = true;
        }
        return wasIngredientDeleted;
    }

    @Transactional
    @Override
    public IngredientDTO replaceByUUID(String uuid, Ingredient updatedIngredient) {
        Ingredient oldIngredient = ingredientRepository.findByUuid(uuid).orElse(null);
        if (oldIngredient != null && updatedIngredient != null) {
            ingredientRepository.findById(oldIngredient.getId())
            .map(ingredient -> {
                ingredient.setName(updatedIngredient.getName());
                ingredient.setUnit(updatedIngredient.getUnit());
                ingredient.setQuantity(updatedIngredient.getQuantity());
                return ingredientAssembler.toModel(ingredientRepository.save(ingredient));
            });
        }
        return null;
    }

}