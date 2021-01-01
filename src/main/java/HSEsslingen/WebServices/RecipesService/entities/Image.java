package HSEsslingen.WebServices.RecipesService.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Image implements Serializable{
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column
    @EqualsAndHashCode.Include
    private String id;

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
