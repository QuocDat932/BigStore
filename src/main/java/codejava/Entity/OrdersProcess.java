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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import codejava.Constant.publicConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(schema = "SYSTEM",name="order_process")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersProcess implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="processdate")
	private Date processData;
	
	@Column(name ="description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn( name="processuserid" ,referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Users user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn( name="orderid" ,referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Orders order;
	
	@Column(name ="process")
	private String process;
	
	public OrdersProcess(Users processUser, Orders order,publicConst.Orderprocess process){
		this.user = processUser;
		this.order = order;
		this.process = String.valueOf(process);
		this.description = "NEW ORDER";
	};
	
}

