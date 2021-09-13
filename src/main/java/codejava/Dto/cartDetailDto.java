package codejava.Dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class cartDetailDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
	private Long idOrder;
	private Long idProduct;
=======
	private int idOrder;
	private int idProduct;
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	private String name;
	private String slug;
	private Double price;
	private Integer quantity;
	private String imgUrl;

}
