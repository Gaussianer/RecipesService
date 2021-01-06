package HSEsslingen.WebServices.RecipesService.services.helper;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ServiceHelper {
    
    public Sort.Direction getSortDirection(String dir){
        Sort.Direction direction;
        if(dir.equalsIgnoreCase("asc")) {
            direction = Sort.Direction.ASC; 
        }
        else {
            direction = Sort.Direction.DESC;
        }
        return direction;
    }



}
