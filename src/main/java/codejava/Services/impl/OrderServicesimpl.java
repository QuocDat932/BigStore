package codejava.Services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.Orders;
import codejava.Entity.Users;
import codejava.Responsitory.OrderRepo;
import codejava.Services.Orderservices;
@Service
public class OrderServicesimpl implements Orderservices{
	@Autowired
	private OrderRepo repo;
	@Override
	@Transactional
	public Orders insert(Orders order) throws Exception {
		try {
			return repo.saveAndFlush(order);
		} catch (Exception e) {
			System.out.println("here");
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int findNewOrder(Users idUser) {
		return  repo.findNewOrder(idUser);
	}
	@Override
	public Orders findById(int id) {
		return repo.findById(id).isPresent()?repo.findById(id).get():null;
	}
	@Override
	public List<Orders> findAll() {
		return repo.findAll();
	}
}
