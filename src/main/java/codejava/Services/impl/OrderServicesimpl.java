package codejava.Services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.Orders;
import codejava.Responsitory.OrderRepo;
import codejava.Services.Orderservices;
@Service
public class OrderServicesimpl implements Orderservices{
	@Autowired
	private OrderRepo repo;
	@Override
	@Transactional
	public Orders insert(Orders order) throws Exception {
		System.out.println(order.getUser().getId());
		try {
			
			return repo.saveAndFlush(order);
		} catch (Exception e) {
			System.out.println("here");
			e.printStackTrace();
			return null;
		}
	}
}
