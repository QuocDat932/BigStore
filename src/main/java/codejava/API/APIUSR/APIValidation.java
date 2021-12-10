package codejava.API.APIUSR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Constant.MessageAPI;
import codejava.Constant.SessionConst;
import codejava.Entity.Products;
import codejava.Entity.Users;
import codejava.Services.AccountGGService;
import codejava.Services.AccountService;
import codejava.Services.ProductsServices;
import codejava.Services.UserServices;

@RestController
@RequestMapping("/api/val")
public class APIValidation {
	@Autowired
	private UserServices UserSrvs;

	@Autowired
	private AccountService AccSrvs;
	@Autowired
	private AccountGGService AccGGSrvs;
	@GetMapping("/username")
	public ResponseEntity<?> postCheckUsername(HttpSession sess){
		try {
			List<String> userName = new ArrayList<>();
			AccSrvs.findAll().forEach(u ->{
				userName.add(u.getUsername());
			});
			
			return ResponseEntity.ok(MessageAPI.message(MessageAPI.SUBMIT, "Everything is done !", userName));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.ok(MessageAPI.message(MessageAPI.FAIL, "Something Wrong", null));
		}
	
	}
	@GetMapping("/email")
	public ResponseEntity<?> postCheckEmail(HttpSession sess){
		try {
			List<String> email = new ArrayList<>();
			UserSrvs.findAll().forEach(u ->{
				email.add(u.getEmail());
			});
			
			return ResponseEntity.ok(MessageAPI.message(MessageAPI.SUBMIT, "Everything is done !", email));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.ok(MessageAPI.message(MessageAPI.FAIL, "Something Wrong", null));
		}
		
		
		
		
	}
	
	
	
	
}
