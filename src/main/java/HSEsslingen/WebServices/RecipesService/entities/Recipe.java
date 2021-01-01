package HSEsslingen.WebServices.RecipesService.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(name="recipe")
public class Recipe implements Serializable{
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "uuid2") // Überprüfen, ob und wie du ein Primary-Key setzt
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column
    @EqualsAndHashCode.Include
    private String id;

    @Column
    @EqualsAndHashCode.Include
    private String title;
    
    @Column(name = "sub_title")
    private String subTitle;

    @Column( length = 1000000 )
    private String description;

    @Column 
    private String category;

    @Column
    private Integer servings;

    @Column
    private Double calories;

    @Column(name = "level_of_difficulty")
    private String levelOfDifficulty;

    @Column(name = "working_time_in_seconds")
    private long workingTimeInSeconds;

    @Column(name = "cooking_time_in_seconds")
    private long cookingTimeInSeconds;

    @Column(name = "resting_time_in_seconds")
    private long restingTimeInSeconds;

    @OneToMany(mappedBy = "recipe", cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addImage(Image image) {
        image.setRecipe(this);
        this.images.add(image);
    }

    public void removeImage(Image image) {
        this.images.remove(image);
        image.setRecipe(null);
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }
}
