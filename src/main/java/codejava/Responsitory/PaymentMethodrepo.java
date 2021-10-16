package codejava.Responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codejava.Entity.PaymentMethod;

@Repository
public interface PaymentMethodrepo extends JpaRepository<PaymentMethod, Integer> {

}
