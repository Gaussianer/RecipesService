package HSEsslingen.WebServices.RecipesService.services.implementation;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import HSEsslingen.WebServices.RecipesService.assemblers.ImageAssembler;
import HSEsslingen.WebServices.RecipesService.assemblers.RecipeAssembler;
import HSEsslingen.WebServices.RecipesService.dtos.ImageDTO;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Image;
import HSEsslingen.WebServices.RecipesService.repositories.ImageRepository;
import HSEsslingen.WebServices.RecipesService.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private final RecipeAssembler recipeAssembler;
    private final ImageAssembler imageAssembler;
    private final PagedResourcesAssembler pagedResourcesAssembler;

    public ImageServiceImpl(ImageRepository imageRepository, RecipeAssembler recipeAssembler, ImageAssembler imageAssembler, PagedResourcesAssembler pagedResourcesAssembler) {
        this.imageRepository = imageRepository;
        this.recipeAssembler = recipeAssembler;
        this.imageAssembler = imageAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }


    @Override
    public ImageDTO findByUUID(String uuid) {
        Image image = imageRepository.findByUuid(uuid).orElse(null);
        if(image != null) {
            return imageAssembler.toModel(image);
        }
        return null;
    }

    @Override
    public RecipeDTO findImageRecipeByUUID(String uuid) {
        Image image = imageRepository.findByUuid(uuid).orElse(null);
        if(image != null && image.getRecipe() != null) {
            return recipeAssembler.toModel(image.getRecipe());
        }
        return null;
    }
}
