package codejava.Responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codejava.Entity.Account;
import codejava.Entity.AccountGG;
import codejava.Entity.Users;
@Repository
public interface AccountGGrepo extends JpaRepository<AccountGG, Integer> {
	
	AccountGG findByEmailAndIdaccount(String email,String idaccount);
	
	AccountGG findByUsers_Id(int userId);
}
