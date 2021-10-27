package codejava.Services;

import java.util.List;

import codejava.Entity.Users;
import codejava.Entity.roles;


public interface UserServices {
List<Users> findAll();
	
	Users findByfullname(String fullname);
	
	Users findByid(int id);
	
	
	Users findByEmail(String email);
	
	void addUser(Users user);
	
	List<Users> UserHaveRoles(List<roles> roles);
	
	
	void delete(Users user);
}
