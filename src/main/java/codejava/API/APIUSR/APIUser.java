package codejava.API.APIUSR;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Constant.MessageAPI;
import codejava.Constant.SessionConst;
import codejava.Entity.Products;
import codejava.Entity.TypeOfProduct;
import codejava.Entity.Users;
import codejava.Services.ProductsServices;
import codejava.Services.TypeOfProductServices;
import codejava.Services.UserServices;

@RestController
@RequestMapping("/api/user")
public class APIUser {
	@Autowired
	private ProductsServices productSrvs;
	@Autowired
	private TypeOfProductServices typrOfProductSrvcs;
	@Autowired
	private UserServices usersSrvs;
	@GetMapping("/currentUser")
	public ResponseEntity<?> getCurrentUser(HttpSession sess){
		try {
			Users session = (Users) sess.getAttribute(SessionConst.CURRENT_USER);
			Users i = session;
			if(Objects.isNull(session)) {
				return ResponseEntity.ok(MessageAPI.message(MessageAPI.FAIL, "Session Null", null));
			}
			session = usersSrvs.findByid(session.getId());
			System.out.println(session.getEmail());
			System.out.println(session.getFullname());
			System.out.println(session.getRole().getDescription());
			System.out.println(session.getAddress());
			System.out.println(session.getPhone());
			return ResponseEntity.ok(MessageAPI.message(MessageAPI.SUBMIT, "Everything is done ", session));
		} catch (Exception e) {
			return ResponseEntity.ok(MessageAPI.message(MessageAPI.FAIL, "Something Wrong ", null));
		}
	}
	
	@GetMapping("/getSubmenu")
	public ResponseEntity<?> getSubmenu(){
		return ResponseEntity.ok(typrOfProductSrvcs.getListTypeOfProduct()); 
	}
}
