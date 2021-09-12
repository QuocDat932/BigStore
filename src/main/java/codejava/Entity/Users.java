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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name ="username")
	private String username;
	
	@Column(name="hashPassword")
	private String hashPassword;
	
	@Column(name="email")
	private String email;
	
	@Column(name="createDate")
	@CreationTimestamp
	//private Timestamp createDate;
	private Timestamp createDate;
	@Column(name="imgUrl")
	private String imgUrl;
	
	@Column(name="isDeleted")
	private Boolean isDeleted;
	
	// Khoa Ngoai
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private roles role;
}

