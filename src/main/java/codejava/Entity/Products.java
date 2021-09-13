package codejava.Entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
<<<<<<< HEAD
@Table(schema = "System",name = "products")
=======
@Table(schema = "SYSTEM",name = "products")
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
public class Products implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;
=======
	@Column(name ="id")
	private int id;
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	
	@Column(name ="name")
	private String name;
	
	@Column(name ="quantity")
	private Integer quantity;
	
	@Column(name ="price")
	private Double price;
	
<<<<<<< HEAD
	@Column(name ="imgUrl")
=======
	@Column(name ="imgurl")
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	private String imgUrl;
	
	@Column(name ="description")
	private String description;
	
<<<<<<< HEAD
	@Column(name ="isDeleted")
=======
	@Column(name ="isdeleted")
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	private Double isDeleted;
	
	@Column(name ="slug")
	private String slug;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
<<<<<<< HEAD
	@JoinColumn(name="typeId", referencedColumnName = "id")
	private TypeOfProduct typeOfProduct;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name="unitId", referencedColumnName = "id")
	private UnitType unitType;
}
/*
 	id				bigint			primary key auto_increment,
	`name`			nvarchar(255)	not null,
	typeId			tinyint,
    foreign key(typeId) references types_of_product(id),
	quantity		int				not null,
	price			decimal(12,3)	not null,
	unitId			tinyint,
    foreign key(unitId) references unit_types(id),
	imgUrl			varchar(255)	null,
	`description`	nvarchar(20)	null,
	isDeleted		bit				not null default 0
 * */
=======
	@JoinColumn(name="typeid", referencedColumnName = "id")
	private TypeOfProduct typeOfProduct;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name="unitid", referencedColumnName = "id")
	private UnitType unitType;
}
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
