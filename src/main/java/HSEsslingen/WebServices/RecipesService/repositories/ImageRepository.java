package HSEsslingen.WebServices.RecipesService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import HSEsslingen.WebServices.RecipesService.entities.Image;

public interface ImageRepository extends JpaRepository<Image, String>{
    
    Optional<Image> findByUuid(String uuid);
    
}