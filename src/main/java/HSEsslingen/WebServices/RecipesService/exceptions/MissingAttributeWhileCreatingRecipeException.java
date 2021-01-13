package HSEsslingen.WebServices.RecipesService.exceptions;

public class MissingAttributeWhileCreatingRecipeException extends RuntimeException {

    private String[] missingAttributes;

    public MissingAttributeWhileCreatingRecipeException(Class clazz, String[] missingAttributes) {
        this.missingAttributes = missingAttributes;
    }
    
    public String[] getMissingAttributes(){
        return this.missingAttributes;
    }

}