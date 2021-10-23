package codejava.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.Order_Process;
import codejava.Responsitory.OrderProcessRepo;
import codejava.Services.Order_ProcessServices;

@Service
public class Order_ProcessServicesImpl implements Order_ProcessServices{
	@Autowired
	private OrderProcessRepo repo;
	@Override
	public List<Order_Process> lstOrder_Process() throws Exception {
		// TODO Auto-generated method stub
		return repo.findAll();
	};
	@Override
	public List<Order_Process> lstOrder_ProcessByOrdId(int ordId) throws Exception {
		// TODO Auto-generated method stub
		return repo.findByOrder_Id(ordId);
	}
}
