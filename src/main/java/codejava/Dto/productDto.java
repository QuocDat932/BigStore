package codejava.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import codejava.Entity.TypeOfProduct;
import codejava.Entity.UnitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class productDto {
	private static final long serialVersionUID = 1L;
	private int id;//
	private String name;//
	private Integer quantity;//
	private Double price;//
	private String imgUrl;
	private String description;
	private Double isDeleted;
	private String slug;
	private TypeOfProduct typeOfProduct;
	private UnitType unitType;
	private int typeof;
	private int unitof;
	private String image;//
	private String summary;//
}
