package codejava.Services;

import java.sql.Date;
import java.util.List;

import codejava.Entity.Orders;
import codejava.Entity.Users;

public interface Orderservices {
	Orders insert(Orders order) throws Exception;
	Orders findNewOrder(Users idUser) throws Exception;
	Orders findById(int id);
	List<Orders> findByUserId(int userId);
	List<Orders> findByParams(int userId, int paymentMethodId, int processId, Date frmDt, Date toDt);

}
