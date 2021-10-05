package codejava.Services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Constant.publicConst.Orderprocess;
import codejava.Dto.cartDetailDto;
import codejava.Entity.OrderDetails;
import codejava.Entity.Orders;
import codejava.Entity.OrdersProcess;
import codejava.Entity.Products;
import codejava.Entity.Users;
import codejava.Responsitory.OrderDetailrepo;
import codejava.Responsitory.OrderProcessRepo;
import codejava.Services.OrderDetailServices;
import codejava.Services.OrderProcessServices;
@Service
public class OrderProcessServicesImpl implements OrderProcessServices{
	@Autowired
	private OrderProcessRepo repo;

	@Override
	public void insert(OrdersProcess o) {
		repo.saveAndFlush(o);
	}

	@Override
	public OrdersProcess findById(int id) {
		return repo.findById(id).isPresent()?repo.findById(id).get():null;
	}

	@Override
	public List<OrdersProcess> findByOrder(Orders o) {
		return repo.findByOrder(o).isPresent()?repo.findByOrder(o).get():null;
	}

	@Override
	public List<OrdersProcess> findByProcess(Orderprocess process) {
		String p = String.valueOf(process);
		return repo.findByProcess(p).isPresent()?repo.findByProcess(p).get():null;
	}

	@Override
	public List<OrdersProcess> findAll() {
		return repo.findAll();
	}

	@Override
	public List<OrdersProcess> findByUser(Users user) {
		return repo.findByUser(user).isPresent()?repo.findByUser(user).get():null;
	}
	
}
