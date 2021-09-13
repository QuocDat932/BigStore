package codejava.Services;

import java.util.List;

import codejava.Entity.Users;
import codejava.Entity.roles;


public interface UserServices {
List<Users> findAll();
	
	Users findByfullname(String fullname);
	
	Users findByid(int id);
	
	Users findByUsernameAndHashPassword(String username, String hashpassword);
	
	Users findByEmail(String email);
	
	void addUser(Users user);
	
	List<Users> UserHaveRoles(List<roles> roles);
	
	Users findByUserName(String findByUserName);
	void delete(Users user);
}
