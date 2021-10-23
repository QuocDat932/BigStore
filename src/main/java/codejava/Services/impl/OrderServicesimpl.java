package codejava.Services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Constant.publicConst;
import codejava.Constant.publicFuncs;
import codejava.Entity.Orders;
import codejava.Entity.Products;
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
			//order.setCreateDate(publicConst.CurrentDtSQL());
			order.setCreateDate(publicConst.CurrentTimestampDtSQL());
			return repo.saveAndFlush(order);
		} catch (Exception e) {
			System.out.println("here");
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Orders findNewOrder(Users idUser) throws Exception {
//		return repo.findNewOrder(idUser).size()==0?null:repo.findNewOrder(idUser).get(0);
		
		if(repo.findNewOrder(idUser)==null) {
			return null;
		}
		
		int id = repo.findNewOrder(idUser);
		
		return  repo.findById(id).isEmpty()?null:repo.findById(id).get();
	}
	@Override
	public Orders findById(int id) {
		return repo.findById(id).isPresent()?repo.findById(id).get():null;
	};
	@Override
	public List<Orders> findByUserId(int userId){
		return repo.findByUser_idOrderByIdDesc(userId);
	};
	@Override
	public List<Orders> findByParams(int UserId, int paymentMethodId, int processId, Date frmDt, Date toDt) {
		List<Orders> result = repo.findByUser_idAndPaymentmethod_idAndProcess_idAndCreateDateBetweenOrderByIdDesc(UserId, paymentMethodId, processId, frmDt, toDt); 
		
		return result;
	};
	@Override
	public List<Orders> findAll(){
		return repo.findAll();
	};
}
