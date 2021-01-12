package HSEsslingen.WebServices.RecipesService.services.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ServiceHelper {
    
    private char ASC = '+';
    private char DESC = '-';

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

    private String getPropertyWithAscendingPrefix(String property) {
        return "+" + property.substring(1);
    }
        
    private String[] getQueryArray(String queryString) {
        return queryString.split(",");
    }

    public List<Sort.Order> getSortOrder(String queryString){
        String[] querys = getQueryArray(queryString);
        List<Sort.Order> sortOrder = new ArrayList();
        for(String query : querys) {
            if(query.charAt(0) == ' '){
                query = getPropertyWithAscendingPrefix(query);
            }
            String property = query.substring(1);
            if(query.charAt(0) == ASC) {
                sortOrder.add(new Sort.Order(Sort.Direction.ASC, property));
            } else if(query.charAt(0) == DESC) {
                sortOrder.add(new Sort.Order(Sort.Direction.DESC, property));
            } else {
                sortOrder.add(new Sort.Order(Sort.Direction.ASC, property));
            }
        }
        return sortOrder;
    }


}
