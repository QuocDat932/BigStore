package codejava.Services;

import codejava.Dto.cartDetailDto;
import codejava.Entity.OrderDetails;
import codejava.Entity.Products;

public interface OrderDetailServices {
	void save(cartDetailDto od) throws Exception;
}
