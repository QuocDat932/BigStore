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

import codejava.Constant.SessionConst;
import codejava.Entity.Products;
import codejava.Entity.Users;
import codejava.Services.ProductsServices;

@RestController
@RequestMapping("/api/user")
public class APIUser {
	@Autowired
	private ProductsServices productSrvs;
	@GetMapping("/currentUser")
	public ResponseEntity<?> getCurrentUser(HttpSession sess){
		if(Objects.isNull(sess.getAttribute(SessionConst.CURRENT_USER))) {
			return ResponseEntity.ok("NO");
		}
		return ResponseEntity.ok((Users)sess.getAttribute(SessionConst.CURRENT_USER));
	}
}
