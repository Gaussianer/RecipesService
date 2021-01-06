package HSEsslingen.WebServices.RecipesService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import HSEsslingen.WebServices.RecipesService.entities.Recipe;


public interface RecipeRepository extends PagingAndSortingRepository<Recipe, String>, JpaSpecificationExecutor<Recipe>{
    
    Optional<Recipe> findByUuid(String uuid);

}
