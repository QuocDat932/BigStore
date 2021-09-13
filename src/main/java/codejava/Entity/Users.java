package codejava.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
<<<<<<< HEAD
import java.util.Date;

=======
//import java.util.Date;
import java.sql.Date;
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
=======

>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
<<<<<<< HEAD
@Table(schema = "System" , name ="users")
=======
@Table(schema = "SYSTEM",name ="users")
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name ="id")
<<<<<<< HEAD
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identity_users")
//    @SequenceGenerator(sequenceName = "identity_users", allocationSize = 1, name = "identity_users")
	private long id;
=======
	private int id;
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name ="username")
	private String username;
	
	@Column(name="hashpassword")
<<<<<<< HEAD
	private String hashpassword;
=======
	private String hashPassword;
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	
	@Column(name="email")
	private String email;
	
<<<<<<< HEAD
//	@Column(name="createdate")
//	@CreationTimestamp
	//private Timestamp createDate;
//	private Timestamp createdate;
	@Column(name="imgurl")
	private String imgurl;
	
	@Column(name="isdeleted")
	private Boolean isdeleted;
=======
	/*@Column(name="createdate"ái bài 
	@CreationTimestamp
	//private Date createDate;
	private Timestamp createDate;
	*/
	@Column(name="imgurl")
	private String imgUrl;
	
	@Column(name="isdeleted")
	private Boolean isDeleted;
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	
	// Khoa Ngoai
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "roleid", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private roles role;
<<<<<<< HEAD
	
	
	
	
}

=======
}
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
