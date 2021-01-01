package HSEsslingen.WebServices.RecipesService.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    @Column(name = "level_of_difficulty")
    private String levelOfDifficulty;

    @Column(name = "working_time_in_seconds")
    private long workingTimeInSeconds;

    @Column(name = "cooking_time_in_seconds")
    private long cookingTimeInSeconds;

    @Column(name = "resting_time_in_seconds")
    private long restingTimeInSeconds;

}
