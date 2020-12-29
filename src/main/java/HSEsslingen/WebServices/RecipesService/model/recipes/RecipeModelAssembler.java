package HSEsslingen.WebServices.RecipesService.model.recipes;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import HSEsslingen.WebServices.RecipesService.controller.RecipeController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RecipeModelAssembler implements RepresentationModelAssembler<Recipe, EntityModel<Recipe>> {

  @Override
  public EntityModel<Recipe> toModel(Recipe recipe) {

    return EntityModel.of(recipe, 
        linkTo(methodOn(RecipeController.class).getRecipeById(recipe.getId())).withSelfRel(),
        linkTo(methodOn(RecipeController.class).getAllRecipes()).withRel("recipes"));
  }
}