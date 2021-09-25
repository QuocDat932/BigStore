package codejava.Dto;

import java.util.HashMap;
import java.util.List;

import codejava.Dto.productDto;
import codejava.Entity.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ListproductDto {
	private static final long serialVersionUID = 1L;
	public List<Products> listTop4;
}
