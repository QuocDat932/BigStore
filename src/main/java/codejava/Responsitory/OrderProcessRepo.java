package codejava.Responsitory;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codejava.Entity.Orders;
import codejava.Entity.OrdersProcess;
import codejava.Entity.Users;




@Repository
public interface OrderProcessRepo extends JpaRepository<OrdersProcess, Integer>{
	Optional<List<OrdersProcess>> findByOrder(Orders order);
	Optional<List<OrdersProcess>> findByUser(Users user);
	Optional<List<OrdersProcess>> findByProcess(String process);
}
