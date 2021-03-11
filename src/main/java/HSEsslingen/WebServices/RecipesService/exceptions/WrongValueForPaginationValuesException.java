package HSEsslingen.WebServices.RecipesService.exceptions;

public class WrongValueForPaginationValuesException extends RuntimeException {

    private String parameterName;
    private Integer value;

    public WrongValueForPaginationValuesException(Class clazz, String parameterName, Integer value) {
        this.parameterName = parameterName;
        this.value = value;
    }

    public String getParameterName() {
        return this.parameterName;
    }
    
    public Integer getValue(){
        return this.value;
    }
}