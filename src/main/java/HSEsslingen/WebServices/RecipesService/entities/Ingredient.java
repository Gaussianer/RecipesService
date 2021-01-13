package HSEsslingen.WebServices.RecipesService.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    
    @ManyToMany(mappedBy = "ingredient", cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        image.setIngredient(this);
        this.images.add(image);
    }

    public void removeImage(Image image) {
        this.images.remove(image);
        image.setIngredient(null);
    }
    
}
