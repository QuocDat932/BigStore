package codejava.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

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

import org.hibernate.annotations.CreationTimestamp;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
<<<<<<< HEAD
@Table(schema = "System", name="orders")
=======
@Table(schema = "SYSTEM",name="orders")
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable{

	/**
	 * 
	 */
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
	
	@Column(name ="address")
	private String address;
	
	@Column(name ="phone")
	private String phone;
	
<<<<<<< HEAD
	@Column(name ="createdDate")
	@CreationTimestamp
	private Timestamp createDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn( name="userId" ,referencedColumnName = "id")
=======
	@Column(name ="createddate")
	@CreationTimestamp
	private Timestamp createDate;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn( name="userid" ,referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	private Users user;
	
}

<<<<<<< HEAD
/*
 
 create table orders
(
	id				bigint			primary key auto_increment,
	userId			bigint,
    foreign key(userId) references users(id),
	createdDate		datetime		not null default now()
);
  
 * */
=======
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
