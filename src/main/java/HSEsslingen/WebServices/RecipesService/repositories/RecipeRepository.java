package HSEsslingen.WebServices.RecipesService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import HSEsslingen.WebServices.RecipesService.entities.Recipe;


public interface RecipeRepository extends JpaRepository<Recipe, String>{
    
    Optional<Recipe> findByTitle(String title);
    Optional<Recipe> findByUuid(String uuid);

}
