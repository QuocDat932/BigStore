package codejava.Responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codejava.Entity.Orders;
@Repository
public interface OrderRepo extends JpaRepository<Orders, Long>{

}
