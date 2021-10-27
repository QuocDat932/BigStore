package codejava.Services;
import java.util.List;

import codejava.Entity.Account;
import codejava.Entity.AccountGG;
import codejava.Entity.Users;

public interface AccountGGService {

	List<AccountGG> findAll();
	
	Users findByEmailAndIdaccount(String email, String idaccount);
	
	void addAccount(AccountGG account);
		
}
