package codejava.Services;
import java.util.List;

import codejava.Entity.Account;

public interface AccountService {

	List<Account> findAll();
	
	Account findByUsername(String username);
	
	
	void addAccount(Account account);
	
	Account findByUsers_Id(int UserId);
}
