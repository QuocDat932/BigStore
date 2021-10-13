package codejava.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "SYSTEM",name="paymentmethod")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="methodname")
	private String methodname;
	
	@Column(name ="description")
	private String description;
}
