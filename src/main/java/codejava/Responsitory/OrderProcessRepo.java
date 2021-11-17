package codejava.Responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import codejava.Entity.Order_Process;
@Repository	
public interface OrderProcessRepo extends JpaRepository<Order_Process, Integer>{
	
	@Query(value = "SELECT * FROM SYSTEM.ORDER_PROCESS WHERE ORDERID = ?1 ORDER BY PROCESSSTEP ASC", nativeQuery = true)
	List<Order_Process> lstOrder_ProcessById(int ordId);
	//List<Order_Process> findByOrder_Id(int ordId);
	
}
