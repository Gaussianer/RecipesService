// package HSEsslingen.WebServices.RecipesService.entities;

// import java.io.Serializable;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;

// import org.hibernate.annotations.GenericGenerator;

// import lombok.EqualsAndHashCode;
// import lombok.Getter;
// import lombok.Setter;
// import lombok.ToString;

// @Entity
// @Getter
// @Setter
// @EqualsAndHashCode(onlyExplicitlyIncluded = true)
// @ToString
// public class Ingredient implements Serializable{
    
// 	private static final long serialVersionUID = 1L;

// 	@Id
//     @GeneratedValue(generator = "uuid2") // Überprüfen, ob und wie du ein Primary-Key setzt
//     @GenericGenerator(name = "uuid2", strategy = "uuid2")
//     @Column
//     @EqualsAndHashCode.Include
//     private String id;

//     @Column
//     @EqualsAndHashCode.Include
//     private String name;
    
//     @Column
//     private String imageURL;

//     @Column
//     private Double calories;


// }
