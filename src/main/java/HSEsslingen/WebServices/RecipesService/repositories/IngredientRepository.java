package HSEsslingen.WebServices.RecipesService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import HSEsslingen.WebServices.RecipesService.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String>{
    
    Optional<Ingredient> findById(String id);

}