package codejava.Services;

import java.util.List;

import codejava.Entity.Orders;
import codejava.Entity.Users;

public interface Orderservices {
	Orders insert(Orders order) throws Exception;
	Orders findNewOrder(Users idUser) throws Exception;
	Orders findById(int id);
	List<Orders> findByParam(int UserId, String Type, int ParamId ,String ParamString);
}
