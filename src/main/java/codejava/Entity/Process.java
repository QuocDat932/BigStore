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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(schema = "SYSTEM",name="process")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Process implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name ="id")
	private int id;
	
	@Column(name ="description")
	private String description;
	
	@Column(name ="slug")
	private String slug;

}/*

*
 create table SYSTEM.Process(
    id number primary key,
    description VARCHAR(50) not null,
  	slug VARCHAR(10) not null,
    createDate date DEFAULT SYSDATE )
  insert all 
  into SYSTEM.Process(id,description,slug) values (1,'Hủy hàng','CANCEL')
  into SYSTEM.Process(id,description,slug) values (2,'xác nhận nhận hàng','CONFIRM')
  into SYSTEM.Process(id,description,slug) values (3,'xác nhận chuyển hàng','APPROVE')
  into SYSTEM.Process(id,description,slug) values (4,'Hủy hàng','REJECT')
  into SYSTEM.Process(id,description,slug) values (5,'Mới đặt hàng','NEW')
  select * from dual

 ALTER TABLE SYSTEM.orders ADD (processId NUMBER)
 update system.orders set processid = 1

 ALTER TABLE SYSTEM.orders
 ADD CONSTRAINT fk_processid
 FOREIGN KEY(processid) REFERENCES SYSTEM.process(id)
*
*/

