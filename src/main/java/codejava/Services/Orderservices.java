package codejava.Services;

import java.util.List;

import codejava.Entity.Orders;
import codejava.Entity.Users;

public interface Orderservices {
	List<Orders> findAll();
	Orders insert(Orders order) throws Exception;
	int findNewOrder(Users idUser) ;
	Orders findById(int id);
}
