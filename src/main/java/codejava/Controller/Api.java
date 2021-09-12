package codejava.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Entity.Users;
import codejava.Entity.userObj;
import codejava.Services.UsersService;

@RestController
public class Api {
	@Autowired
	private UsersService repo;
	@GetMapping("/api/print/{id}")
	public ResponseEntity<?> doGetAPI(@PathVariable("id") Long userid) {
		System.out.println("*****************************"+userid);
		Users findx = repo.findByid(userid);
		
		return ResponseEntity.ok(findx);
	}
}
