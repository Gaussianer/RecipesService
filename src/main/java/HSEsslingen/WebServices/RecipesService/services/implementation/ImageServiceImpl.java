package HSEsslingen.WebServices.RecipesService.services.implementation;

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

    public ImageServiceImpl(ImageRepository imageRepository, 
    RecipeAssembler recipeAssembler, ImageAssembler imageAssembler, PagedResourcesAssembler pagedResourcesAssembler) {
        this.imageRepository = imageRepository;
        this.recipeAssembler = recipeAssembler;
        this.imageAssembler = imageAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Override
    public CollectionModel<ImageDTO> findAll(int page, int size, String[] sort, String dir) {
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
        Page<Image> images = imageRepository.findAll(pageRequest);

        if(! CollectionUtils.isEmpty(images.getContent())) {
            return pagedResourcesAssembler.toModel(images, imageAssembler);
        }
        return null;
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
    
    @Transactional
    @Override
    public ImageDTO insert(Image image) {
        String uuid = UUID.randomUUID().toString();
        image.setUuid(uuid);
        return imageAssembler.toModel(imageRepository.save(image));
    }

    @Transactional
    @Override
    public boolean removeByUUID(String uuid) {
        Image image = imageRepository.findByUuid(uuid).orElse(null);
        boolean wasImageDeleted = false;
        if(image != null) {
            imageRepository.deleteById(image.getId());
            wasImageDeleted = true;
        }
        return wasImageDeleted;
    }

    @Transactional
    @Override
    public ImageDTO replaceByUUID(String uuid, Image updatedImage) {
        Image oldImage = imageRepository.findByUuid(uuid).orElse(null);
        if (oldImage != null && updatedImage != null) {
            imageRepository.findById(oldImage.getId())
            .map(image -> {
                image.setName(updatedImage.getName());
                image.setUrl(updatedImage.getUrl());
                return imageAssembler.toModel(imageRepository.save(image));
            });
        }
        return null;
    }
}
