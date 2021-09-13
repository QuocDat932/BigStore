package codejava.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.SequenceGenerator;
=======
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SYSTEM",name = "roles")
public class roles implements Serializable{

	/**
	 * 
	 */
<<<<<<< HEAD
	
	private static final long serialVersionUID = 1L;
	
=======
	private static final long serialVersionUID = 1L;
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	@Id
	@Column(name ="id")
	private int id; // primary key
	
	@Column(name ="description")
	private String description;
}
<<<<<<< HEAD
/*
 (
	id				tinyint			primary key auto_increment,
	`description`	nvarchar(20)	not null unique
)
 * */
=======

>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
