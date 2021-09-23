package codejava.API.APIUSR;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Entity.Products;
import codejava.Services.ProductsServices;

@RestController
@RequestMapping("/api/user")
public class APIproduct {
	@Autowired
	private ProductsServices productSrvs;
	@GetMapping("/top4product")
	public ResponseEntity<?> goGettop4product(@RequestParam("param") String param){
		System.out.println("Param >> "+ param);
		List<Products> top4 = productSrvs.findtop4Bytype(param);
		return ResponseEntity.ok(top4);
	}
}
