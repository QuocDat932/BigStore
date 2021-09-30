package codejava.Responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import codejava.Dto.cartDetailDto;
import codejava.Entity.OrderDetails;
@Repository
public interface OrderDetailrepo extends JpaRepository<OrderDetails, Integer> {

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO system.Order_details(orderId, productId, price, quantity) "
			 + "VALUES (:#{#dto.idOrder}, :#{#dto.idProduct}, :#{#dto.price}, :#{#dto.quantity})",
			 nativeQuery = true)
	void saveDetail(@Param("dto") cartDetailDto dto);
}
