package codejava.Services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import codejava.Entity.Orders;
import codejava.Entity.Users;

public interface Orderservices {
	Orders insert(Orders order) throws Exception;
	Orders findNewOrder(Users idUser) throws Exception;
	Orders findById(int id);
	List<Orders> findByUserId(int userId);
	List<Orders> findByParams(int paymentMethodId, int processId, LocalDateTime frmDt, LocalDateTime toDt);
	List<Orders> findAll();
	Orders findByOrderId(int ID);
}
