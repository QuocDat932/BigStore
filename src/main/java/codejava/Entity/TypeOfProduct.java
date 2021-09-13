package codejava.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
<<<<<<< HEAD
@Table(schema = "System",name ="types_of_product")
=======
@Table(schema = "SYSTEM",name ="types_of_product")
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
=======
	@Column(name="id")
	private int id;
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	
	@Column(name="description")
	private String description;
	
<<<<<<< HEAD
	@Column(name="isDeleted")
	private boolean isDeleted;
=======
	@Column(name="isdeleted")
	private boolean isDeleted;
	
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	@Column(name="slug")
	private String slug;
	
}
<<<<<<< HEAD
/*
create table unit_types
(
	id				tinyint			primary key auto_increment,
	`description`	nvarchar(20)	null,
	isDeleted		bit				not null default 0
)
 * */
=======
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
