package codejava.Dto;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class cartDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
<<<<<<< HEAD
	private Long userId;
	private String address;
	private String phone;
	private Timestamp createDate;
	private HashMap<Long, cartDetailDto> listDetail = new HashMap<>();
=======
	private int userId;
	private String address;
	private String phone;
	private Timestamp createDate;
	private HashMap<Integer, cartDetailDto> listDetail = new HashMap<>();
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	private Integer totalQuantity = 0;
	private Double totalPrice = 0D;
}
