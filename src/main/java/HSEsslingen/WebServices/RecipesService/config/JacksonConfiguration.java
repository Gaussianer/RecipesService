package HSEsslingen.WebServices.RecipesService.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Configuration 
public class JacksonConfiguration { 
    public JacksonConfiguration(ObjectMapper objectMapper) { 
        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false)); 
    } 
}
