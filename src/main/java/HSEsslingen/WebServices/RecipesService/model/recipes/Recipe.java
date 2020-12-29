package HSEsslingen.WebServices.RecipesService.model.recipes;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Recipe {
    
    @Id
    @GeneratedValue(generator = "uuid2") // Überprüfen, ob und wie du ein Primary-Key setzt
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column
    private String id;

    @Column
    private String title;
    
    @Column
    private String subTitle;

    @Column( length = 1000000 )
    private String description;

    // @Columns MUSS NOCH Ausgearbeitet werden?! Tuple, Paare? Wie gehst du da vor
    // private Ingredient[] ingredients;
  
    // @Column
    // private Double quantity; // 500, 1, 20, ...
    
    // @Column
    // private String unit; // Stück, Kg, Liter, etc. ...

    @Column
    private String imageURL;

    @Column // Eigener Datentyp noch möglich!
    private String category;

    @Column
    private Integer servings;

    @Column
    private Double calories;

    @Column
    private String levelOfDifficulty;

    @Column
    private long workingTimeInSeconds;

    @Column
    private long cookingTimeInSeconds;

    @Column
    private long restingTimeInSeconds;

    public Recipe(String title, String subTitle, String description, String imageURL, String category, Integer servings, 
    Double calories, String levelOfDifficulty, long workingTimeInSeconds, long cookingTimeInSeconds, long restingTimeInSeconds) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.imageURL = imageURL;
        this.category = category;
        this.servings = servings;
        this.calories = calories;
        this.levelOfDifficulty = levelOfDifficulty;
        this.workingTimeInSeconds = workingTimeInSeconds;
        this.cookingTimeInSeconds = cookingTimeInSeconds;
        this.restingTimeInSeconds = restingTimeInSeconds;
    }

    public Recipe() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return this.subTitle;
    }
        
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
        
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return this.imageURL;
    }
            
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
            
    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Integer getServings() {
        return this.servings;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getCalories() {
        return this.calories;
    }
            
    public void setLevelOfDifficulty(String levelOfDifficulty) {
        this.levelOfDifficulty = levelOfDifficulty;
    }

    public String getLevelOfDifficulty() {
        return this.levelOfDifficulty;
    }
            
    public void setWorkingTimeInSeconds(long workingTimeInSeconds) {
        this.workingTimeInSeconds = workingTimeInSeconds;
    }

    public long getWorkingTimeInSeconds() {
        return this.workingTimeInSeconds;
    }
             
    public void setCookingTimeInSeconds(long cookingTimeInSeconds) {
        this.cookingTimeInSeconds = cookingTimeInSeconds;
    }

    public long getCookingTimeInSeconds() {
        return this.cookingTimeInSeconds;
    }
                
    public void setRestingTimeInSeconds(long restingTimeInSeconds) {
        this.restingTimeInSeconds = restingTimeInSeconds;
    }

    public long getRestingTimeInSeconds() {
        return this.restingTimeInSeconds;
    }

    @Override
    public boolean equals(Object o) {
  
      if (this == o)
        return true;
      if (!(o instanceof Recipe))
        return false;
        Recipe recipe = (Recipe) o;
      return Objects.equals(this.id, recipe.id) && Objects.equals(this.title, recipe.title)
          && Objects.equals(this.subTitle, recipe.subTitle);
    }
  
    @Override
    public int hashCode() {
      return Objects.hash(this.id, this.title, this.subTitle);
    }
  
    @Override
    public String toString() {
      return "Recipe{" + "id=" + this.id + ", title='" + this.title + '\'' + ", subTitle='" + this.subTitle + '\'' + '}';
    }

}
