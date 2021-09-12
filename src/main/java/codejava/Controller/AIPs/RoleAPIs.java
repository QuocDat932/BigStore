package codejava.Controller.AIPs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import codejava.Entity.Users;
import codejava.Entity.roles;
import codejava.Responsitory.RolesRepository;
import codejava.Services.UsersService;

@RestController
public class RoleAPIs {
	

	@Autowired
	private RolesRepository RS;
	
	@GetMapping("/apis/roles")
	public  ResponseEntity<?> getAll(){
		return ResponseEntity.ok(RS.findAll());
	}
	@GetMapping("/apis/roles/{id}")
	public  ResponseEntity<?> getAll(@PathVariable Integer id){
		return ResponseEntity.ok(RS.findById(id));
	}
	@GetMapping("/apis/role/create")
	public  ResponseEntity<?> getAdd(){
		roles r = new roles();
		r.setDescription("hi");
		return ResponseEntity.ok(RS.save(r));
	}
}
