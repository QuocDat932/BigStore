package codejava.Services;

import java.util.List;

import codejava.Dto.cartDetailDto;
import codejava.Entity.OrderDetails;
import codejava.Entity.Products;

public interface OrderDetailServices {
	void save(cartDetailDto od) throws Exception;
	List<OrderDetails> listOrdDtlById(int ordId) throws Exception;
}
