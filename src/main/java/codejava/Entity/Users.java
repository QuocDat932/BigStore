package codejava.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
//import java.util.Date;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SYSTEM",name ="users")
public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name="email")
	private String email;
	/*@Column(name="createdate"ái bài 
	@CreationTimestamp
	//private Date createDate;
	private Timestamp createDate;
	*/
	@Column(name="imgurl")
	private String imgUrl;
	
	@Column(name="isdeleted")
	private Boolean isDeleted;
	
	@Column(name="type_account")
	private String type_account;
	
	@Column(name="phone")
	private String phone;
	
	
	@Column(name="address")
	private String address;
	
	// Khoa Ngoai
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "roleid", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private roles role;
}