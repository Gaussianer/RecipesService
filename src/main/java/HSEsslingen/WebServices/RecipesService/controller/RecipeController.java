package HSEsslingen.WebServices.RecipesService.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.util.StringUtils;
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
import HSEsslingen.WebServices.RecipesService.exceptions.FieldAttributeNotFoundException;
import HSEsslingen.WebServices.RecipesService.exceptions.RecipeNotFoundException;
import HSEsslingen.WebServices.RecipesService.services.ImageService;
import HSEsslingen.WebServices.RecipesService.services.RecipeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;


@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    private RecipeService recipeService;
    private String[] tempPropertys = {"id", "title", "subTitle", "description", "category", "servings", "calories", "levelOfDifficulty", "workingTimeInSeconds", "cookingTimeInSeconds", "restingTimeInSeconds", "images", "ingredients", "links"}; 
    public RecipeController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
    }

    private HashMap<String, String> getSpecificationKeyValuePairs(Specification<Recipe> recipeSpec) {
        List<String> recipePropertys = Arrays.asList(tempPropertys);

        HashMap<String, String> finalHashMap = new HashMap<>();
        String[] specs = recipeSpec.toString().split("\\[");
        String[]  exceptedValues;
        String[]  pathValues;

        String determinedExpectedValue = null;
        String determinedPathValue;
        for (String spec: specs){
            if(spec.startsWith("expectedValue")){
                exceptedValues = spec.split(", converter=Converter");
                for(String expetedValue: exceptedValues){
                    if(expetedValue.length() > 14) {
                        determinedExpectedValue = expetedValue.substring(14);
                    }
            }
            } else if(spec.startsWith("dateFormat=null, onTypeMismatch=EMPTY_RESULT], ")){
                pathValues = spec.split("dateFormat=null, onTypeMismatch=EMPTY_RESULT], ");
                for(String pathValue: pathValues){
                    if(pathValue.length() > 5){
                        String[] splittedPathValues = pathValue.substring(5).split("]]");
                        for(String splittedValue: splittedPathValues) {
                                if(recipePropertys.contains(splittedValue)){
                                    determinedPathValue = splittedValue;
                                    if((determinedPathValue != null) && (determinedExpectedValue != null)) {
                                        finalHashMap.put(determinedPathValue, determinedExpectedValue);
                                    }
                                }
                            }
                        }
                    }
                }
        }
        return finalHashMap;
    }

    @GetMapping
    (produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "Get all recipes", notes = "This API delivers all recipes")
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
            HttpServletRequest request ) throws JsonProcessingException, RecipeNotFoundException {
            
            CollectionModel<RecipeDTO> recipes = null;
            HashMap<String, String> specificationKeyValuePairs = null;
            if(recipeSpec != null) {
            specificationKeyValuePairs =  getSpecificationKeyValuePairs(recipeSpec);
            } 
            
            recipes = recipeService.findAll(offset, limit, sort, fields, recipeSpec, specificationKeyValuePairs);

        
        String[] tempFields; 
        if(fields.equals("")) {
           tempFields = tempPropertys;
        } else {
            List<String> tempList = new ArrayList<>();
            for(String field : fields.split(",")){
                tempList.add(StringUtils.trimAllWhitespace(field));
            }
            List<String> recipePropertys = Arrays.asList(tempPropertys);
            List<String> wrongAttributes = new ArrayList<>();
            for(String field : tempList.toArray(new String[0])){
                if(!recipePropertys.contains(field)){
                    wrongAttributes.add(field);
                }
            }
            if(wrongAttributes.toArray(new String[0]).length > 0) {
                throw new FieldAttributeNotFoundException(Recipe.class, null, wrongAttributes.toArray(new String[0]));
            }
            tempFields = tempList.toArray(new String[0]);
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
    HttpServletRequest request) throws JsonProcessingException, RecipeNotFoundException {

        RecipeDTO recipeDTO = recipeService.findByUUID(recipeId);

        String[] tempFields;
        if(fields.equals("")) {
            String[] tempPropertys = {"id", "title", "subTitle", "description", "category", "servings", "calories", "levelOfDifficulty", "workingTimeInSeconds", "cookingTimeInSeconds", "restingTimeInSeconds", "images", "ingredients", "links"}; 
            tempFields = tempPropertys;
        } else {
            List<String> tempList = new ArrayList<>();
            for(String field : fields.split(",")){
                tempList.add(StringUtils.trimAllWhitespace(field));
            }
            tempFields = tempList.toArray(new String[0]);
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
    public ResponseEntity<?> addRecipe(@RequestBody RecipeDTO recipe) {
            if (recipe != null) {
                RecipeDTO recipeDTO = recipeService.insert(recipe);
                return ResponseEntity.created(recipeDTO.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(recipeDTO);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    (value = "/{recipeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> removeRecipeByUUID(@PathVariable String recipeId) throws RecipeNotFoundException {
            if (recipeId != null && recipeId != "") {
                RecipeDTO deletedRecipe = recipeService.removeByUUID(recipeId);
                if (deletedRecipe != null) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } 
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    (value = "/{recipeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> replaceRecipeByUUID(@PathVariable String recipeId, @RequestBody RecipeDTO recipe) throws RecipeNotFoundException {
            if (recipeId != null && recipe != null) {
                RecipeDTO recipeDTO = recipeService.replaceByUUID(recipeId, recipe);
                return ResponseEntity.ok(recipeDTO);
            }
            // ERROR WERFEN
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    (value = "/{recipeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> updateRecipeByUUID(@PathVariable String recipeId, @RequestBody RecipeDTO recipe) {
            if (recipeId != null) {
                RecipeDTO recipeDTO = recipeService.updateByUUID(recipeId, recipe);
                return ResponseEntity.ok(recipeDTO);
            }
            // ERROR WERFEN
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
