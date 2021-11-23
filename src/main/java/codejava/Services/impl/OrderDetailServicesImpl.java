package codejava.Services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Dto.cartDetailDto;
import codejava.Entity.OrderDetails;
import codejava.Entity.Products;
import codejava.Responsitory.OrderDetailrepo;
import codejava.Responsitory.ProductsRepository;
import codejava.Services.OrderDetailServices;
@Service
public class OrderDetailServicesImpl implements OrderDetailServices{
	@Autowired
	private OrderDetailrepo repo;
	@Autowired
	private ProductsRepository Rrepo;
	
	@Override
	@Transactional
	public void save(cartDetailDto p) throws Exception {
		//lưu order detail
		repo.saveDetail(p);
		
		//trừ số lượng sản phẩm
		Products product = Rrepo.findById(p.getIdProduct()).get();
		int quantity = product.getQuantity()-p.getQuantity();
		System.out.println(" >>>>>>>> số lượng ban đầu :" +  product.getQuantity());	
		System.out.println(" >>>>>>>> số lượng cần trừ  :" +  p.getQuantity());	
		System.out.println(" >>>>>>>> số lượng sau khi trừ  :" +  quantity);
		product.setQuantity(quantity);
		// nếu xét số lượng có sẵn 
		if(quantity<=0) {
			product.setIsdeleted(0);
			System.out.println(" >>>>>>>> số lượng <= 0 nên  sản phẩm sẽ được ẩn! ");
		}
		Rrepo.saveAndFlush(product);
		
	};
	@Override
	public List<OrderDetails> listOrdDtlById(int ordId) throws Exception{
		return repo.findByOrder_Id(ordId);
	};
}
