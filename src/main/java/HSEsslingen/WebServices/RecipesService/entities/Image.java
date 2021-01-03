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
public class Image implements Serializable{
    
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private String id;

    @Column(unique = true)
    @EqualsAndHashCode.Include
    private String uuid;

    @Column(name = "image_name")
    @EqualsAndHashCode.Include
    private String name;
    
    @Column(name = "image_url")
    @EqualsAndHashCode.Include
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
