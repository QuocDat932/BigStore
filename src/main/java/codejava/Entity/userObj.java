package codejava.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "SYSTEM", name = "SYS_USERS")
public class userObj implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Id
	 @Column(name = "userid")
	 private int userid ;
	 
	 @Column(name = "username")
	 private String username;
	 
	 @Column(name = "password")
	 private String password;
	 
	 @Column(name = "active")
	 private int active;
}
