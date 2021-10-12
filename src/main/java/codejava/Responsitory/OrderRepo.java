package codejava.Responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import codejava.Entity.Orders;
import codejava.Entity.Products;
import codejava.Entity.Users;
@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer>{
//	@Query("select SYSTEM.ORDERS.id, SYSTEM.ORDERS.createddate, SYSTEM.ORDERS.phone, SYSTEM.ORDERS.userid from SYSTEM.ORDERS where SYSTEM.ORDERS.userid like ?1 order by SYSTEM.ORDERS.id desc")
	@Query("SELECT max(o.id) FROM Orders o where o.user like ?1 order by o.id desc")
	Integer findNewOrder(Users idUser);
	
	List<Orders> findByUser_id(int userId);
	
	// Find By UserId and PaymentMethodId
	List<Orders> findByUser_idAndPaymentmethod_idOrderByIdDesc(int userId, int PaymentMethodId);
	
	// Find By UserId and PaymentSTS
	List<Orders> findByUser_idAndPaymentstsOrderByIdDesc(int userId, String PaymentSts);
	
	// Find By UserId and OrderProcess
	List<Orders> findByUser_idAndProcess_idOrderByIdDesc(int userId, int ProcessId);
}
