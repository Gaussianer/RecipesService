package HSEsslingen.WebServices.RecipesService.assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import HSEsslingen.WebServices.RecipesService.controller.ImageController;
import HSEsslingen.WebServices.RecipesService.controller.IngredientController;
import HSEsslingen.WebServices.RecipesService.controller.RecipeController;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Image;
import HSEsslingen.WebServices.RecipesService.entities.Ingredient;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecipeAssembler implements RepresentationModelAssembler<Recipe, RecipeDTO> {

    @Override
    public RecipeDTO toModel(Recipe recipeEntity) {
                    
        RecipeDTO recipeDTO = new RecipeDTO(
            recipeEntity.getUuid(), 
            recipeEntity.getTitle(),
            recipeEntity.getSubTitle(),
            recipeEntity.getDescription(),
            recipeEntity.getCategory(),
            recipeEntity.getServings(),
            recipeEntity.getCalories(),
            recipeEntity.getLevelOfDifficulty(),
            recipeEntity.getWorkingTimeInSeconds(),
            recipeEntity.getCookingTimeInSeconds(),
            recipeEntity.getRestingTimeInSeconds()
            );

        for(Ingredient ingredient : recipeEntity.getIngredients()){
            recipeDTO.add(linkTo(methodOn(IngredientController.class).getIngredientByUUID(ingredient.getUuid())).withRel("ingredients"));
        }

            for(Image image : recipeEntity.getImages()){
            recipeDTO.add(linkTo(methodOn(ImageController.class).getImageByUUID(image.getUuid())).withRel("images"));
        }
        recipeDTO.add(linkTo(methodOn(RecipeController.class).getRecipeByUUID(recipeEntity.getUuid())).withSelfRel());
        return recipeDTO;
    }
}