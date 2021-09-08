package codejava.Services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.userObj;
import codejava.Responsitory.Userrepository;
import codejava.Services.UserServices;
@Service
public class UserServicesImpl implements UserServices{
	@Autowired
	private Userrepository repo;
@Override
public List<userObj> listAll() {
	// TODO Auto-generated method stub
	return repo.findAll();
	//return repo.all(1);
}
@Override
public void save(userObj newuser) {
	// TODO Auto-generated method stub
	repo.saveAndFlush(newuser);
}
@Override
public userObj findByid(int userid) {
	// TODO Auto-generated method stub
	Optional<userObj> result = repo.findById(userid);
	return result.isPresent() ? result.get() : null;
	
}
@Override
public void remove(userObj removeuser) {
	// TODO Auto-generated method stub
	repo.delete(removeuser);
}
}
