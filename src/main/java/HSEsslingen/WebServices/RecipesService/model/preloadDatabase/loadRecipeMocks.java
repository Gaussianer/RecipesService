package HSEsslingen.WebServices.RecipesService.model.preloadDatabase;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import HSEsslingen.WebServices.RecipesService.model.recipes.RecipeRepository;
import HSEsslingen.WebServices.RecipesService.model.recipes.Recipe;


@Configuration
public class loadRecipeMocks {
    private static final Logger log = LoggerFactory.getLogger(loadRecipeMocks.class);

    @Bean
    CommandLineRunner initDatabase(RecipeRepository recipeRepository) {

    return args -> {
      log.info("Preloading " + recipeRepository.save(new Recipe("Vegetarische Spinat-Gemüse-Lasagne mit Tomatensoße", "Sehr einfach und lecker", 
      "Karotten, Champignons und Zwiebel schälen, schneiden und zuerst die Zwiebelwürfel in einer Pfanne anbraten, bis sie glasig sind. Danach das restliche Gemüse dazugeben und kurz mitbraten. 5 EL Milch dazugeben und den Rahmspinat darin erhitzen, bis die Blätter nicht mehr gefroren sind und die Rahmsoße leicht eingedickt ist. Jetzt den Frischkäse sowie die Gemüsebrühe dazugeben und gut verrühren. Fünf Minuten köcheln lassen, bis die Soße schön eingedickt ist. Zum Schluss die Knoblauchzehe dazu pressen und mit Salz, Pfeffer und Chili abschmecken. Das Spinatgemisch darf ruhig gut würzig sein, da der Spinat beim Backen relativ viel Salz schluckt. In einem separaten Topf die Butter erhitzen, bis sie vollständig zerlaufen ist. Das Mehl unter Rühren hinzugeben und zu einer glatten Masse verrühren. Unter stetem Rühren nun die passierten Tomaten dazugeben, danach direkt die Sahne dazugießen und alles kurz aufkochen. Die Knoblauchzehe dazupressen und mit Salz und Pfeffer abschmecken. Auch hier muss nicht am Salz gespart werden. Mit der Tomatensoße und dem Spinat dünn den Boden einer Auflaufform bedecken und jetzt abwechselnd Lasagneplatten, Spinatgemisch und Tomatensoße darin schichten. Mit der Soße abschließen und lückenlos mit Reibekäse bestreuen. Bei 180 Grad Umluft ca. 30 Minuten backen. Nach dem Backen noch fünf bis zehn Minuten im ausgeschalteten Backofen stehen lassen, dann bleibt die Lasagne perfekt in Form und zerläuft nicht. Tipp: Sehr lecker ist es auch, auf eine Schicht fein geschnittene Fetascheiben zu legen, bevor die nächste Lasagneplattenschicht kommt."
      , "https://img.chefkoch-cdn.de/rezepte/2114131340630587/bilder/1331935/crop-600x400/vegetarische-spinat-gemuese-lasagne-mit-tomatensosse.jpg", "Pasta", 1, 133.00, "normal", 1500, 2700, 600
    )));
    
    log.info("Preloading " + recipeRepository.save(new Recipe("Pizza - Pfannkuchen", "Schmeckt besser wie eine Pizza aus Hefeteig", 
      "Aus den obigen Zutaten einen Pfannkuchenteig herstellen und diesen 10 Minuten ausquellen lassen. Margarine in die Pfanne geben und 1/4 des Teiges hineingießen. Den Belag draufgeben und mit dem Pizzagewürz nach Geschmack bestreuen. Den Käse draufgeben und kurz den Deckel auflegen. Nach 6-8 Minuten ist die Pfannenpizza servierfertig. Tipp: Man kann natürlich den Belag abwandeln, z.B. Salami, Paprika etc. So kann man gut auch mal Reste aus dem Kühlschrank verwerten."
      , "https://img.chefkoch-cdn.de/rezepte/348291119895455/bilder/869682/crop-600x400/pizza-pfannkuchen.jpg", "Resteverwertung", 1, 325.00, "simple", 800, 0, 0
    )));
    
    log.info("Preloading " + recipeRepository.save(new Recipe("TEST", "test", 
      "Aus den obigen Zutaten einen Pfannkuchenteig herstellen und diesen 10 Minuten ausquellen lassen. Margarine in die Pfanne geben und 1/4 des Teiges hineingießen. Den Belag draufgeben und mit dem Pizzagewürz nach Geschmack bestreuen. Den Käse draufgeben und kurz den Deckel auflegen. Nach 6-8 Minuten ist die Pfannenpizza servierfertig. Tipp: Man kann natürlich den Belag abwandeln, z.B. Salami, Paprika etc. So kann man gut auch mal Reste aus dem Kühlschrank verwerten."
      , "https://img.chefkoch-cdn.de/rezepte/348291119895455/bilder/869682/crop-600x400/pizza-pfannkuchen.jpg", "Resteverwertung", 1, 325.00, "simple", 1,1,1
    )));

    log.info("Preloading " + recipeRepository.save(new Recipe("Fisch Burger", "Von meinen Kindern liebevoll Spongebob Burger genannt", 
      "Die Fischstäbchen nach Anleitung braten. Die Brötchen aufschneiden und mit der Remoulade und dem Ketchup bestreichen. Die Zwiebeln in Scheiben schneiden und auf der unteren Seite des Brötchens verteilen. Mit jeweils einem Salatblatt, 3 Fischstäbchen und ein paar Scheiben der Gewürzgurken belegen. Die obere Brötchenhälfte auflegen. Superlecker, meine Kinder lieben es, und wenn es mal schnell gehen soll, ist dies eine gute Alternative und vor allem ist es nicht immer die gleiche Art, Fischstäbchen zu servieren!"
      , "https://img.chefkoch-cdn.de/rezepte/1185951224578186/bilder/1094123/crop-600x400/fisch-burger.jpg", "Burger", 1, 464.00, "simple", 800, 600, 0
    )));

    };
  }
}
