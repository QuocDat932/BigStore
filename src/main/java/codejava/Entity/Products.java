package codejava.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(schema = "SYSTEM",name = "products")
public class Products implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="name")
	private String name;
	
	@Column(name ="quantity")
	private Integer quantity;
	
	@Column(name ="price")
	private Double price;
	
	@Column(name ="imgurl")
	private String imgUrl;
	
	@Column(name ="description")
	private String description;
	
	@Column(name ="isdeleted")
	private Integer isdeleted;
	
	@Column(name ="slug")
	private String slug;
	
	@Column(name ="frmdate")
	private LocalDateTime frmdate;
	
	@Column(name ="todate")
	private LocalDateTime todate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name="typeid", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private TypeOfProduct typeOfProduct;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name="unitid", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private UnitType unitType;
	
}
