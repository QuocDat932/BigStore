package codejava.Responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codejava.Entity.Account;
import codejava.Entity.Users;
@Repository
public interface Accountrepo extends JpaRepository<Account, Integer> {
	Account findByUsername(String username);
	
}
