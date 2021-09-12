package codejava.Controller.AIPs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import codejava.Entity.Users;
import codejava.Services.UsersService;

@RestController
public class ProductAPIs {
	
	@Autowired
	private UsersService service;
	
	@GetMapping("/apis/users")
	public  ResponseEntity<?> getAll(){
		
		return ResponseEntity.ok(service.findAll());
	}
}
