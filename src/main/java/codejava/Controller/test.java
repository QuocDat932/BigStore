package codejava.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import codejava.Entity.userObj;
import codejava.Services.UsersService;
public class test {
	@Autowired
	private UsersService repo;
	
	public void list() {
//		List<userObj> user = repo.listAll();
	}
	public void main(String args[]) {
		list();
	}
}
