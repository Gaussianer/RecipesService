package HSEsslingen.WebServices.RecipesService.exceptions;

import java.util.HashMap;

public class UnknowSelectionAttribute extends RuntimeException {

    private HashMap<String, String> specificationKeyValuePairs;

    public UnknowSelectionAttribute(Class clazz, HashMap<String, String> specificationKeyValuePairs) {
    this.specificationKeyValuePairs = specificationKeyValuePairs;
    }

    public HashMap<String, String>  getSpecificationKeyValuePairs (){
        return this.specificationKeyValuePairs;
    }

}
