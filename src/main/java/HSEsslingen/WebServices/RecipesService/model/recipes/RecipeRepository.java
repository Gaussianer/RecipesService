package HSEsslingen.WebServices.RecipesService.model.recipes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, String>{
    
}
