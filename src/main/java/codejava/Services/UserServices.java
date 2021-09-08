package codejava.Services;

import java.util.List;

import codejava.Entity.userObj;

public interface UserServices {
	List<userObj> listAll();
	void save(userObj newuser);
	userObj findByid(int userid);
	void remove(userObj removeuser);
}
