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
@Entity
<<<<<<< HEAD
@Table(schema = "System", name="order_details")
=======
@Table(schema = "SYSTEM",name="order_details")
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
=======
	@Column(name="id")
	private int id;
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	
	
	@Column(name="price")
	private Double price;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
<<<<<<< HEAD
	@JoinColumn(name = "orderId", referencedColumnName = "id")
	private Orders order;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name="productId", referencedColumnName = "id")
=======
	@JoinColumn(name = "orderid", referencedColumnName = "id")
	private Orders order;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name="productid", referencedColumnName = "id")
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	private Products product;
	
}


/*
 create table order_details
(
	id				bigint			primary key auto_increment,
	orderId			bigint references orders(id),
	productId		bigint,
    foreign key(productId) references products(id),
	price			decimal(12,3)	not null,
	quantity		int				not null
)
 */