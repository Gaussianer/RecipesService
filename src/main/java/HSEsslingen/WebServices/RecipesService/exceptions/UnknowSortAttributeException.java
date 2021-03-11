package HSEsslingen.WebServices.RecipesService.exceptions;


public class UnknowSortAttributeException extends RuntimeException {

    private String attributeName;

    public UnknowSortAttributeException(Class clazz, String attributeName) {
    this.attributeName = attributeName;
    }

    public String getAttributeName(){
        return this.attributeName;
    }

}
