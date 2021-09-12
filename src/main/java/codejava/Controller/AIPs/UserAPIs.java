package codejava.Controller.AIPs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import codejava.Entity.Users;
import codejava.Responsitory.RolesRepository;
import codejava.Services.UsersService;

@RestController
public class UserAPIs {
	
	@Autowired
	private UsersService US;
	@Autowired
	private RolesRepository RS;
	
	@GetMapping("/apis/users")
	public  ResponseEntity<?> getAll(){
		return ResponseEntity.ok(US.findAll());
	}
	
	@GetMapping("/apis/user/create")
	public  ResponseEntity<?> getCreateUser(){
		Users u = new Users();
		u.setId(3111111L);
		u.setUsername("Thien1123");
		u.setEmail("Thien@1");
		u.setFullname("Thien");
		u.setHashpassword("123");
		u.setRole(RS.findById(1));
		u.setIsdeleted(false);
		return ResponseEntity.ok(US.addUser(u));
	}
}
