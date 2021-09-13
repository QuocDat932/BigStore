package codejava.Controller.AIPs;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import codejava.Entity.Users;
import codejava.Entity.roles;
import codejava.Responsitory.RolesRepository;
import codejava.Services.UsersService;
import codejava.Constains.CRUD;
@RestController
public class RoleAPIs {
	

	@Autowired
	private RolesRepository RS;
	
	@GetMapping("/apis/roles")
	public  ResponseEntity<?> getAll(){
		return ResponseEntity.ok(RS.findAll());
	}
	@GetMapping("/apis/role/create")
	public  ResponseEntity<?> getAdd(){
	
		try {
			roles r = new roles();
			r.setDescription("hi");
			r = RS.save(r);
			System.out.println(CRUD.CREATE_ACTION);
			return ResponseEntity.ok(r);
		} catch (Exception e) {
			System.out.println("ERROR : "+e);
			return ResponseEntity.ok(CRUD.ERROR_ACTION);
		}
		
	}
	@GetMapping("/apis/roles/{id}")
	public  ResponseEntity<?> getFindById(@PathVariable Integer id){
		roles r= RS.findById(id);
		if(!Objects.isNull(r)) {
			System.out.println(CRUD.DELETE_ACTION);
			return ResponseEntity.ok(RS.findById(id));
		}{	
			System.out.println(CRUD.ERROR_ID);
			return ResponseEntity.ok(CRUD.ERROR_ACTION);
		}
	}
	@GetMapping("/apis/roles/{id}/delete")
	public  ResponseEntity<?> getId(@PathVariable Integer id){
		roles r= RS.findById(id);
		if(!Objects.isNull(r)) {
			RS.delete(r);
			System.out.println(CRUD.DELETE_ACTION);
			return ResponseEntity.ok(RS.findAll());
		}{	
			System.out.println(CRUD.ERROR_ID);
			return ResponseEntity.ok(CRUD.ERROR_ACTION);
		}
		
	}
}
