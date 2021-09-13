package codejava.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "System" , name ="users")
public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name ="id")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identity_users")
//    @SequenceGenerator(sequenceName = "identity_users", allocationSize = 1, name = "identity_users")
	private long id;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name ="username")
	private String username;
	
	@Column(name="hashpassword")
	private String hashpassword;
	
	@Column(name="email")
	private String email;
	
//	@Column(name="createdate")
//	@CreationTimestamp
	//private Timestamp createDate;
//	private Timestamp createdate;
	@Column(name="imgurl")
	private String imgurl;
	
	@Column(name="isdeleted")
	private Boolean isdeleted;
	
	// Khoa Ngoai
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "roleid", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private roles role;
	
	
	
	
}

