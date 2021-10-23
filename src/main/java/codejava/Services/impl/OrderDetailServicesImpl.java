package codejava.Services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Dto.cartDetailDto;
import codejava.Entity.OrderDetails;
import codejava.Entity.Products;
import codejava.Responsitory.OrderDetailrepo;
import codejava.Services.OrderDetailServices;
@Service
public class OrderDetailServicesImpl implements OrderDetailServices{
	@Autowired
	private OrderDetailrepo repo;
	@Override
	@Transactional
	public void save(cartDetailDto p) throws Exception {
		// TODO Auto-generated method stub
		repo.saveDetail(p);
	};
	@Override
	public List<OrderDetails> listOrdDtlById(int ordId) throws Exception{
		return repo.findByOrder_Id(ordId);
	};
}
