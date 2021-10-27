package codejava.Services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.Users;
import codejava.Entity.roles;
import codejava.Responsitory.Userrepository;

import codejava.Responsitory.Userrepository;
import codejava.Services.UserServices;
@Service
public class UserServicesImpl implements UserServices{
	@Autowired
	private Userrepository repo;
	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Users findByfullname(String fullname) {
		// TODO Auto-generated method stub
		return repo.findByfullname(fullname);
	}
	@Override
	public Users findByid(int id) {
		Optional<Users> result = repo.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}
	
	@Override
	public void addUser(Users user) {
		repo.saveAndFlush(user);
		/*for(Users s : users) {
			System.out.println("Name: "+ s.getFullname());
			System.out.println("Email: "+ s.getEmail());
		}*/
		
	}
	
	@Override		   
	public List<Users> UserHaveRoles(List<roles> roles) {
		// TODO Auto-generated method stub
		for(roles s : roles) {
			System.out.println("Des: "+ s.getDescription());
			System.out.println("Des: "+ s.getId());
		}
		return repo.findByRoleIn(roles);
	}

	@Override
	public void delete(Users user) {
		// TODO Auto-generated method stub
		repo.delete(user);
		System.out.println("Delete Thành Công!");
	}
}
