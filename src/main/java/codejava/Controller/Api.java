package codejava.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Entity.userObj;
//import codejava.Services.UserServices;

@RestController
public class Api {
//	@Autowired
//	private UserServices repo;
//	@GetMapping("/api/print")
//	public ResponseEntity<?> doGetAPI(@RequestParam("id") int userid) {
//		System.out.println("*****************************"+userid);
//		userObj findx = repo.findByid(userid);
//		System.out.println(findx.getActive());
//		return ResponseEntity.ok(findx);
//	}
}
