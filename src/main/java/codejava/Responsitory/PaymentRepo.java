package codejava.Responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import codejava.Entity.Orders;
import codejava.Entity.PaymentMethod;
import codejava.Entity.Products;
import codejava.Entity.Users;
@Repository
public interface PaymentRepo extends JpaRepository<PaymentMethod, Integer>{
}
