package codejava.Dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class objChartOrderTotal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String description;
	private Integer total;
	
}