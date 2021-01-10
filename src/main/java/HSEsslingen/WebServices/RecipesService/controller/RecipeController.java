package HSEsslingen.WebServices.RecipesService.controller;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import HSEsslingen.WebServices.RecipesService.dtos.RecipeDTO;
import HSEsslingen.WebServices.RecipesService.entities.Recipe;
import HSEsslingen.WebServices.RecipesService.services.ImageService;
import HSEsslingen.WebServices.RecipesService.services.RecipeService;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;


@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    (produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> getAllRecipes(
        @And({
            @Spec(path = "title", spec = Equal.class),
            @Spec(path = "subTitle", spec = Equal.class),
            @Spec(path = "description", spec = Equal.class),
            @Spec(path = "category", spec = Equal.class),
            @Spec(path = "calories", spec = Equal.class),
            @Spec(path = "levelOfDifficulty", spec = Equal.class),
            @Spec(path = "workingTimeInSeconds", spec = Equal.class),
            @Spec(path = "cookingTimeInSeconds", spec = Equal.class),
            @Spec(path = "restingTimeInSeconds", spec = Equal.class)
            }) Specification<Recipe> recipeSpec,
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit, 
            @RequestParam(required = false) String sort,
            @RequestParam(required = false,defaultValue = "") String fields,
            HttpServletRequest request ) throws JsonProcessingException {

        CollectionModel<RecipeDTO> recipes = recipeService.findAll(offset, limit, sort, fields, recipeSpec);

        String[] tempFields;
        if(fields.equals("")){
            String[] tempPropertys = {"id", "title", "subTitle", "description", "category", "servings", "calories", "levelOfDifficulty", "workingTimeInSeconds", "cookingTimeInSeconds", "restingTimeInSeconds", "links"}; 
            tempFields = tempPropertys;
        } else {
                tempFields = fields.split(",");
        }
        
        if(request.getHeader("Accept").equals(MediaType.APPLICATION_XML_VALUE)) {
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("recipeFilter", SimpleBeanPropertyFilter.filterOutAllExcept(tempFields));
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writer(filterProvider).writeValueAsString(recipes);
            if(recipes != null) {
                return ResponseEntity.ok(xml);
            }
                return ResponseEntity.notFound().build();
        } 
        else {
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("recipeFilter", SimpleBeanPropertyFilter.filterOutAllExcept(tempFields));
            MappingJacksonValue mapper = new MappingJacksonValue(recipes);
            mapper.setFilters(filterProvider);
            if(recipes != null) {
                return ResponseEntity.ok(mapper);
            }
            return ResponseEntity.notFound().build();
        } 
    }

    @GetMapping
    (value = "/{recipeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> getRecipeByUUID(@PathVariable String recipeId, 
    @RequestParam(required = false,defaultValue = "") String fields, 
    HttpServletRequest request) throws JsonProcessingException {

        RecipeDTO recipeDTO = recipeService.findByUUID(recipeId);

        String[] tempFields;
        if(fields.equals("")) {
            String[] tempPropertys = {"id", "title", "subTitle", "description", "category", "servings", "calories", "levelOfDifficulty", "workingTimeInSeconds", "cookingTimeInSeconds", "restingTimeInSeconds", "links"}; 
            tempFields = tempPropertys;
        } else {
            tempFields = fields.split(",");
        }
        
        if(request.getHeader("Accept").equals(MediaType.APPLICATION_XML_VALUE)) {
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("recipeFilter", SimpleBeanPropertyFilter.filterOutAllExcept(tempFields));
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writer(filterProvider).writeValueAsString(recipeDTO);
            if(recipeDTO != null) {
                return ResponseEntity.ok(xml);
            }
                return ResponseEntity.notFound().build();
        } 
        else {
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("recipeFilter", SimpleBeanPropertyFilter.filterOutAllExcept(tempFields));
            MappingJacksonValue mapper = new MappingJacksonValue(recipeDTO);
            mapper.setFilters(filterProvider);
            if(recipeDTO != null) {
                return ResponseEntity.ok(mapper);
            }
            return ResponseEntity.notFound().build();
        } 
    }

    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        try {
            if (recipe != null) {
                RecipeDTO recipeDTO = recipeService.insert(recipe);
                return ResponseEntity.created(recipeDTO.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(recipeDTO);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    (value = "/{recipeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> removeRecipeByUUID(@PathVariable String recipeId) {
        try {
            if (recipeId != null && recipeId != "") {
                boolean recipeWasDeleted = recipeService.removeByUUID(recipeId);
                if (!recipeWasDeleted) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    (value = "/{recipeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> replaceRecipeByUUID(@PathVariable String recipeId, @RequestBody Recipe recipe) {
        try {
            if (recipeId != null && recipe != null) {
                RecipeDTO recipeDTO = recipeService.replaceByUUID(recipeId, recipe);
                return ResponseEntity.ok(recipeDTO);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    (value = "/{recipeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> updateRecipeByUUID(@PathVariable String recipeId, @RequestBody Recipe recipe) {
        try {
            if (recipeId != null) {
                RecipeDTO recipeDTO = recipeService.updateByUUID(recipeId, recipe);
                return ResponseEntity.ok(recipeDTO);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
