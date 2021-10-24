package codejava.Services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.Account;
import codejava.Entity.AccountGG;
import codejava.Entity.Users;
import codejava.Responsitory.AccountGGrepo;
import codejava.Responsitory.Accountrepo;
import codejava.Responsitory.Userrepository;
import codejava.Services.AccountGGService;
import codejava.Services.AccountService;
@Service
public class AccountGGServiceImp implements AccountGGService{

	@Autowired
	private AccountGGrepo repo ;
	
	@Autowired
	private Userrepository userRepo;
	
	@Override
	public List<AccountGG> findAll() {
		return repo.findAll();
	}


	@Override
	public void addAccount(AccountGG account) {
		// TODO Auto-generated method stub
		repo.saveAndFlush(account);
	}


	@Override
	public Users findByEmailAndIdaccount(String email, String idaccount) {
		// TODO Auto-generated method stub
		Users	  resultUser = null;
		AccountGG resultAccountGG  = repo.findByEmailAndIdaccount(email, idaccount);
		      	  
		if(resultAccountGG != null) {
			resultUser = userRepo.findByEmail(resultAccountGG.getEmail());
		}
		return resultUser; 
	}


}
