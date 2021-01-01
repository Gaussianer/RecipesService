package HSEsslingen.WebServices.RecipesService.assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import HSEsslingen.WebServices.RecipesService.controller.RecipeController;
import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecipeAssembler implements RepresentationModelAssembler<Recipe, RecipeDTO> {

    @Override
    public RecipeDTO toModel(Recipe recipeEntity) {
        RecipeDTO recipeDTO = new RecipeDTO(
            recipeEntity.getId(), 
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

        recipeDTO.add(linkTo(methodOn(RecipeController.class).findRecipeIngredients(recipeEntity.getId())).withRel("ingredients"));
        recipeDTO.add(linkTo(methodOn(RecipeController.class).findRecipeImages(recipeEntity.getId())).withRel("images"));
        recipeDTO.add(linkTo(methodOn(RecipeController.class).findById(recipeEntity.getId())).withSelfRel());
        return recipeDTO;
    }
}