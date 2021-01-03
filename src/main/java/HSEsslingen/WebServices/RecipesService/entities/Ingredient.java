package HSEsslingen.WebServices.RecipesService.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Ingredient implements Serializable {
    
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private String id;

    @Column(unique = true)
    @EqualsAndHashCode.Include
    private String uuid;

    @Column(name = "ingredient_name")
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "ingredient_quantity")
    private Double quantity;

    @Column(name = "ingredient_unit")
    private  String unit;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
