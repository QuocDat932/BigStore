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
	private Long idOrder;
	private Long idProduct;
	private String name;
	private String slug;
	private Double price;
	private Integer quantity;
	private String imgUrl;

}
