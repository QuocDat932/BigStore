package codejava.Controller.AIPs;

import java.util.Objects;

import org.hibernate.internal.SessionFactoryRegistry.ObjectFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import codejava.Entity.Users;
import codejava.Responsitory.RolesRepository;
import codejava.Services.UsersService;
import codejava.Constains.CRUD;
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
		
		try {
			Users u = new Users();
			u.setUsername("T3");
			u.setEmail("T3@");
			u.setFullname("T3");
			u.setHashpassword("123");
			u.setRole(RS.findById(1));
			u.setIsdeleted(false);
			US.addUser(u);
			System.out.println(CRUD.CREATE_ACTION);
			return ResponseEntity.ok(u);
		} catch (Exception e) {
			System.out.println("ERROR : "+e);
			return ResponseEntity.ok(CRUD.ERROR_ACTION);
		}
	}
	@GetMapping("/apis/user/{id}")
	public  ResponseEntity<?> getById(@PathVariable long id){
		
		Users u = US.findByid(id);
		if(!Objects.isNull(u)){
			return ResponseEntity.ok(US.findAll());
		}{
			return ResponseEntity.ok(CRUD.ERROR_ACTION);
		}
	}
	@GetMapping("/apis/user/{id}/delete")
	public  ResponseEntity<?> getDelete(@PathVariable long id){
		
		Users u = US.findByid(id);
		if(!Objects.isNull(u)){
			US.delete(u);
			System.out.println("Deleted!");
			return ResponseEntity.ok(US.findAll());
		}{
			System.out.println("Error! Could not  Find This User ID !".toUpperCase());
			return ResponseEntity.ok(CRUD.ERROR_ACTION);
		}
	}
}
