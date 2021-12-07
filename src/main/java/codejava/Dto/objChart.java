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
public class objChart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String String1;
	private String String2;
	
	private Integer total;
	
}