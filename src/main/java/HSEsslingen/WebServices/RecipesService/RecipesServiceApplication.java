package HSEsslingen.WebServices.RecipesService;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;

@SpringBootApplication
public class RecipesServiceApplication implements WebMvcConfigurer{

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SpecificationArgumentResolver());
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

	public static void main(String[] args) {
		SpringApplication.run(RecipesServiceApplication.class, args);
	}

}
