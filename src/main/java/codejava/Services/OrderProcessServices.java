package codejava.Services;

import java.util.List;

import codejava.Entity.Orders;
import codejava.Entity.OrdersProcess;
import codejava.Entity.Users;
import codejava.Constant.publicConst;

public interface OrderProcessServices {
	void insert(OrdersProcess o);
	OrdersProcess findById(int id);
	List<OrdersProcess> findAll();
	List<OrdersProcess> findByOrder(Orders order);
	List<OrdersProcess> findByUser(Users user);
	List<OrdersProcess> findByProcess(publicConst.Orderprocess process);
	
}
