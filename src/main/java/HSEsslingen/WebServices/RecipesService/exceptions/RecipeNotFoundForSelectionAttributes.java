package HSEsslingen.WebServices.RecipesService.exceptions;


public class RecipeNotFoundForSelectionAttributes extends RuntimeException {

    private String[] params;

    public RecipeNotFoundForSelectionAttributes(Class clazz, String id, String[] searchParamsMap) {
        this.params = searchParamsMap;
    }

    public String[] getParams(){
        return this.params;
    }

}