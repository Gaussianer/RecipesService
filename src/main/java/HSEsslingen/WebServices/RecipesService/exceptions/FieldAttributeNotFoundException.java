package HSEsslingen.WebServices.RecipesService.exceptions;

public class FieldAttributeNotFoundException extends RuntimeException {

    private String id;
    private String[] params;

    public FieldAttributeNotFoundException(Class clazz, String id, String[] searchParamsMap) {
        this.id = id;
        this.params = searchParamsMap;
    }

    public String getID() {
        return this.id;
    }
    
    public String[] getParams(){
        return this.params;
    }

}