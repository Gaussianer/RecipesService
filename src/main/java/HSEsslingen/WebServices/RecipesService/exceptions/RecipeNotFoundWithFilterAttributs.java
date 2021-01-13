package HSEsslingen.WebServices.RecipesService.exceptions;

import java.util.HashMap;

public class RecipeNotFoundWithFilterAttributs extends RuntimeException {

    private HashMap<String, String> specificationKeyValuePairs;

    public RecipeNotFoundWithFilterAttributs(Class clazz, HashMap<String, String> specificationKeyValuePairs) {
    this.specificationKeyValuePairs = specificationKeyValuePairs;
    }

    public HashMap<String, String>  getSpecificationKeyValuePairs (){
        return this.specificationKeyValuePairs;
    }

}