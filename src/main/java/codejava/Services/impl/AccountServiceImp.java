package codejava.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.Account;
import codejava.Responsitory.Accountrepo;
import codejava.Services.AccountService;
@Service
public class AccountServiceImp implements AccountService{

	@Autowired
	Accountrepo repo ;
	
	
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Account findByUsername(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}

	@Override
	public void addAccount(Account account) {
		repo.saveAndFlush(account);
	}

	@Override
	public Account findByUsers_Id(int userId) {
		return repo.findByUsers_Id(userId);
	}




}
